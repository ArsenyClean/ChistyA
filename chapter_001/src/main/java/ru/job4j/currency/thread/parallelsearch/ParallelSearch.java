package ru.job4j.currency.thread.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ThreadSafe
public class ParallelSearch {

        public class Finder extends SimpleFileVisitor<Path> {

            private final PathMatcher matcher;

            public Finder(String pattern) {
                matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
            }

            void find(Path file) {
                Path name = file.getFileName();
                if (name != null && matcher.matches(name)) {
                        files.add(file.toString());
                    }

            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                find(file);
                return FileVisitResult.CONTINUE;
            }
        }

    private final String root;
    private final String text;
    private final List<String> exts;

    @GuardedBy("this")
    private final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();

    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    private String patternMaker() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String ex : exts) {
            stringBuilder.append(ex).append(",");
        }
        if (stringBuilder.length() <= 0) {
            try {
                throw new NoSuchFileException("ThereIsNoFilesWithSuchFormat");
            } catch (NoSuchFileException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return "*.{" + stringBuilder.toString() + "}";
    }

    public void init() throws InterruptedException {
        AtomicBoolean finish = new AtomicBoolean(false);
        Thread search = new Thread() {
            @Override
            public void run() {
                Path startingDir = Paths.get(root);
                String pattern = patternMaker();

                Finder finder = new Finder(pattern);
                try {
                    Files.walkFileTree(startingDir, finder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish.set(true);
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                while (!finish.get() || !files.isEmpty()) {
                    try {
                        if (!files.isEmpty()) {
                            String string;
                            string = files.poll();
                            FileInputStream fis = new FileInputStream(string);
                            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                            BufferedReader bufferedReader = new BufferedReader(isr);
                            String line = bufferedReader.readLine();
                            while (line != null) {
                                Pattern pattern = Pattern.compile(text);
                                Matcher matcher = pattern.matcher(line);
                                if (matcher.find()) {
                                    paths.add(string);
                                    break;
                                }
                                line = bufferedReader.readLine();
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("File wasnt open  ");
                    }
                }
            }
        };
        search.start();
        read.start();
        search.join();
        read.join();

        for (String s : paths) {
            System.out.println(s);
        }
    }
}