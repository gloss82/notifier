INSERT INTO public.message_status (id, name)
VALUES (1, 'UNKNOWN');
INSERT INTO public.message_status (id, name)
VALUES (2, 'QUEUED');
INSERT INTO public.message_status (id, name)
VALUES (3, 'SENT');
INSERT INTO public.message_status (id, name)
VALUES (4, 'DELIVERED');
INSERT INTO public.message_status (id, name)
VALUES (5, 'DEAD');

INSERT INTO public.message_importance (id, name)
VALUES (1, 'INFO');
INSERT INTO public.message_importance (id, name)
VALUES (2, 'ALERT');

INSERT INTO public.push_subscription (phone, created, updated, token, active)
VALUES ('79630429597', '2019-12-19 00:00:00.000000', '2019-12-19 05:30:15.181920',
        'ievie5AeS3MeChomerohgahph8joo1Igh7aefooweeRai5ohCaehoo', true);

INSERT INTO public.message (id, phone, created, content, updated, status, importance)
VALUES ('eca7b5d8-32a6-4e8c-a36e-b0ebdb96ddab', '79630429597', '2019-12-18 19:16:03.234153',
        'Test message!!!', null, 2, 1);