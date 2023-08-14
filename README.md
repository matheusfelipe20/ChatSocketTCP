<h1 align="center">ChatSocketTCP - Sistema Distribuido</h1>
As mudanças no código permitem que os clientes enviem e recebam mensagens uns dos outros a partir do servidor. Com isso, as mensagens podem ser direcionadas a um cliente (usuário) em específico, ou
serem eviadas para todos os clientes conectados. Por fim, para que tudo isso desse certo também foi necessário garantir que as operações de comunicação seja tratada de forma assímcrona, logo, ela permite
que vários clientes interajam simultaneamente no chat através do servidor.
<h2>🧠Funcionalidades (Adicional)</h2>

- Os clientes podem se conectar ao servidor por meio de um username(nome de usuário).
- Os clientes podem trocar/enviar mensagens livremente para todos os clientes conectados no servidor, como se fosse um chat geral.
- Os clientes podem enviar mensagens privadas a um usuário específico, basta usar: '@username mensagem'

<h2>🛑Tratamento de Erros</h2>
<h3>1. Erro de Conexão com o Servidor ou Cliente</h3>

- Essa exceção foi criada para caso o servidor não esteja dispovível, ou a porta esteja incorreta.
<h3>2. Erro ao Fechar Recurso</h3>

- Essa exceção evitar que o código seja interrompido caso ocorra erro durante o fechamento de recursos.
<h3>3. Erro de Leitura/Escrita</h3>

- Essa exceção pode ocorrer quando a conexão é perdida inesperadamente, então ela lida com fechamento de conexões e recursos.
<h3>4. Erro ao Manipular Threads</h3>

- Essa exceção evita que os erros lançados dentro das threads interrompa o funcionamento do código, como também ajuda a capturar esses erros.

<h2>😃Aluno: Matheus Felipe Bandeira Oliveira</h2>
