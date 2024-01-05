# Descrição

Por enquanto isso é um bate-papo bem simples e modesto que usei para aplicar alguns conceitos em Java. Usei Sockets e Threads para essa simples aplicação, além de interface Swing.
Era apenas para ser um teste, mas ideias vieram e pretendo aplicá-las ao longo do tempo. Não sei no que isso vai ser tornar, veremos.

Bom, são apenas três classes mesmo. Duas delas são executáveis: Client e Server. Os nomes são bem intuitivos, então acredito que não preciso explicar. A outra classe, Connection,
implementa a interface Runnable e é usada pelo Server para gerenciar, com o auxílio de Threads, os usuários que conectam-se ao servidor pelo Socket.

# Tecnologias

* **Swing**: biblioteca gráfica para a criação de interfaces gráficas de usuário (GUI) em Java.
* **Threads**: unidades de execução concorrentes dentro de um programa.
* **Sockets**: _endpoints_ para a comunicação entre diferentes programas em uma rede.
