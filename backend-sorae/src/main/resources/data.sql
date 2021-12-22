INSERT INTO ATIVIDADE(titulo, descricao, data_Postagem, data_Entrega, nota, tipo) VALUES('Atividade 1 - Matemática - Potenciação', 'A atividade consiste em Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor', '2021-09-29 00:00:00',  '2021-10-03 20:25:00', null, 'ATIVIDADE')
INSERT INTO ATIVIDADE(titulo, descricao, data_Postagem, data_Entrega, nota, tipo) VALUES('Atividade 4 - Português - Separação silábica', 'A atividade consiste em Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor', '2020-11-29 00:00:00',  '2021-01-03 20:25:00', 9.0, 'PROVA')
INSERT INTO USUARIO(ID, nome, login, senha) VALUES (1, 'Mateus', 'teste','$2a$12$fwf7ocsV/7mD2f8dfiqW5eaIH4LYUDfmx2KYTE4/9Iqw6/6ax4ym6')
INSERT INTO PERFIL(ID, DESCRICAO) VALUES (1, 'PROFESSOR')
INSERT INTO PERFIL(ID, DESCRICAO) VALUES(2, 'ALUNO')
INSERT INTO PERFIL(ID, DESCRICAO) VALUES(3, 'ADMIN')

INSERT INTO USUARIO_PERFIL(USUARIO_ID, PERFIL_ID) VALUES(1, 1)
INSERT INTO USUARIO_PERFIL(USUARIO_ID, PERFIL_ID) VALUES(1, 3)