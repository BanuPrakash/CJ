
insert into  users (username, password, enabled) values ('harry', '$2a$12$CEjZh3OwAnLb22elRboOH.8J8YNRTHalonmNU2jiRLZKYfLBACbN.', 1);
insert into  users (username, password, enabled) values ('anne', '$2a$12$osrc5Qec5Pj84RG6HTXikuJ4/ASsg2p91P7ONaOVbaGcqSOS25IUC', 1);

insert into authorities(username, authority) values ('harry', 'ROLE_USER');
insert into authorities(username, authority) values ('anne', 'ROLE_USER');
insert into authorities(username, authority) values ('anne', 'ROLE_ADMIN');

