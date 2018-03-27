




insert into role(id, role_name) values
(1, 'Admin'),
(2, 'usual_user'),
(3, 'smart_user'),
(4, 'stupid_user');

insert into users(id, name, role_id) values
(1, 'Arseny', 1),
(2, 'Zasha', 2);

insert into rules(id, rules_for_role) values
(1, 1),
(2, 2),
(3, 2),
(4, 3);

insert into role_rules(id, rules_id, role_id) values
(1, 1, 1),
(2, 2, 1),
(3, 1, 2),
(4, 2, 2);

insert into catalogy(catalogy_id, catalogy_name) values
(1, 'catalogy 1'),
(2, 'catalogy 2');

insert into attache(attached_id, attached_name) values
(1, 'attache 1'),
(2, 'attache 2');

insert into items(item_id, user_items, attached_name, catalogy_inform) values
(1, 1, 1, 2),
(2, 2, 2, 1);

insert into states(state_id, state_name) values
(1, 1),
(2, 2);

insert into coments(comments_id, comment_words) values
(1, 1),
(2, 2);