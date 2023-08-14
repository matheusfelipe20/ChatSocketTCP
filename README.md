<h1 align="center">ChatSocketTCP - Sistema Distribuido</h1>
<h2>Funcionalidades</h2>
<h3>A alteração do código é basicamente para que seja possível a troca de mensagem entre clientes, logo, o usuário ao se conectar irá poder trocar mensagem entre os usuários conextados como se fosse um chat global.</h3>
<h3>Dessa forma, no servidor também será possivel visualizar as mensagens dos usuários, e quem mandou qual mensagem.</h3>
<h2>Tratamento de Erros</h2>
<h3>1. Erro de Conexão com o Servidor ou Cliente</h3>
-  Essa exceção foi criada para caso o servidor não esteja dispovível, ou a porta esteja incorreta.
<h3>2. Erro ao Fechar Recurso</h3>
-  Essa exceção evitar que o código seja interrompido caso ocorra erro durante o fechamento de recursos.
<h3>3. Erro de Leitura/Escrita</h3>
-  Essa exceção pode ocorrer quando a conexão é perdida inesperadamente, então ela lida com fechamento de conexões e recursos.
<h3>4. Erro ao Manipular Threads</h3>
-  Essa exceção evita que os erros lançados dentro das threads interrompa o funcionamento do código, como também ajuda a capturar esses erros.
<h3>5. Erro ao Processar Mensagem</h3>
-  Erros como má formatação ou manipulação inadequada de dados podem ocorrer, então essa exceção notifica o usuário desses problemas.
