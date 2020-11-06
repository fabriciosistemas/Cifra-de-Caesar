package trabalhoCifra; // pacote ao qual o arquivo pertence
import java.io.FileNotFoundException; // importação de FileNotFoundException
import java.io.FileReader; // importação de FileReader
import java.io.IOException; // importação de IOException
import java.io.PrintWriter; // importação de PrintWriter
import javax.swing.JDialog; // importação de JDialog
import javax.swing.JOptionPane; // importação de JOptionPane
import javax.swing.JTextArea; // importação de JTextArea

public class Menu { // Criação de classe Menu
    String lista = "1 – Cifrar Mensagem e Exportar txt\n2 – Decifrar mensagem a partir de arquivo\n3 – Visualizar e Editar anotação\nS – SAIR"; // criação de itens do Menu

    // Método que exibe o menu usando JOptionPane.showInputDialog

    public String mostrarMenu() { // Método que exibe o menu usando JOptionPane.showInputDialog

        return JOptionPane.showInputDialog(null, lista); // Chamada para o JOptionPane

    } // fim do método mostrarMenu

    public void executaMenu() throws IOException { // Método de execução do Menu

        String opcao = ""; // Inicializa a variável opcao

        while (!opcao.equalsIgnoreCase("s")) { // enquanto opção for diferente de S ou s, fica no loop de exibição do Menu

            opcao = mostrarMenu(); // Chamada para o método mostrarMenu

            switch (opcao) { // Inicialização do switch/case

                case "1": // caso o valor inserido seja 1, executa o código abaixo
                	
                    executaOp1(); // Chamada ao método executaOpção1
                    
                    break; // sai do switch e volta a avaliar a condição do while

                case "2": // caso o valor inserido seja 2, executa o código abaixo

                    executaOp2(); // Chamada ao método executaOpção2

                    break; // sai do switch e volta a avaliar a condição do while

                case "3": // caso o valor inserido seja 3, executa o código abaixo

                    executaOp3(); // Chamada ao método executaOpção3

                    break; // sai do switch e volta a avaliar a condição do while

                case "S": // caso o valor inserido seja S, executa o código do case "s"

                case "s": // caso o valor inserido seja s, executa o código abaixo
                    JOptionPane.showMessageDialog(null, "Até logo!"); // Exibe uma caixa de texto com a mensagem "Adeus"

                    break; // sai do switch e volta a avaliar a condição do while

                default: // O que será feito caso o entrado não tenha sido um dos predefinidos acima

                    JOptionPane.showMessageDialog(null, "Não entendi sua escolha\nVou exibir o menu novamente"); // Será mostrada uma mensagem e voltará ao Menu Principal

            } // fim do switch

        } // fim do while

    } // fim do método executaMenu

    private void executaOp1() throws IOException { // método da opção 1
    	JOptionPane.showMessageDialog(null, "Escolha a pasta"); // JOptionPane para exibição da mensagem na tela
    	JFileChooserDemo pasta; //declarar variavel para receber a pasta
        pasta = new JFileChooserDemo(); // atribuindo valor
        
        try { // tentar executar o código abaixo
        	String escolhepasta = pasta.analisaCaminho(); // chamada ao método analiseCaminho da classe JFileChooserDemo
	        String arquivo = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a variável arquivo armazenará o nome do arquivo
	        
	        String x = escolhepasta.toString() + "/" + arquivo; // a variável x faz uma concatenação entre a pasta e o arquivo a ser salvo, ficando o endereço completo
	         
	    	PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter é a classe que vai escrever no arquivo através do objeto escrever a partir do endereço do arquivo e do charset
	    	
	    	JTextArea textoArea = new JTextArea(20,20); // define uma caixa de texto de 20 x 20
	    	JOptionPane.showMessageDialog(null, textoArea,"Entre com a mensagem desejada",JOptionPane.OK_CANCEL_OPTION); // Abre o local de entrada de texto
	    	String mensagem = textoArea.getText(); // a variável mensagem recebe o texto digitado na área de texto
	    	
	        int senha = Integer.parseInt(JOptionPane.showInputDialog("Entre com a senha")); // a variável senha vai armazenar a chave de criptografia da mensagem
	        
	        Criptografar c = new Criptografar(); // instancia o objeto c
	        escrever.println(c.cifrar(mensagem, senha)); // é escrito no arquivo x a mensagem criptografada a partir da senha
	        escrever.close(); // encerra o processo de escrita, finalizando o arquivo
	        
	        JOptionPane.showMessageDialog(null, "Mensagem criptografada em: " + x); // mensagem final da operação, indicando onde o arquivo foi salvo

        } // fim do try
        catch (SecurityException securityException) // catch para falha na escrita
        {
        	JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
	        System.exit(1); //encerra o programa
        } // fim do catch
        catch (FileNotFoundException fileNotFoundException) // catch para erro no endereço do arquivo
        {
        	JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro ao criar o arquivo. Possível erro na escolha do diretório. Terminando."); // Mensagem de erro
	        System.exit(1); //encerra o programa
        } // fim do catch
    } // fim do método executaOp1

    private void executaOp2() throws IOException { // método da opção 2
    	JOptionPane.showMessageDialog(null, "Escolha o arquivo"); // JOptionPane que mostra a mensagem "Escolha o arquivo" na tela
    	JFileChooserDemo arquivo; //declarar variavel
    	arquivo = new JFileChooserDemo(); // atribuindo valor
        try { // tentar executar o código abaixo
	        String arquivofinal = arquivo.analisaCaminho(); // chamada ao método analiseCaminho da classe JFileChooserDemo
	        
	    	int senha = Integer.parseInt(JOptionPane.showInputDialog("Entre com a senha:")); // a variável senha vai armazenar a chave de criptografia da mensagem
	    	StringBuffer resultado= new StringBuffer(); // cria o objeto result de StringBuffer
	    	FileReader fr = new FileReader(arquivofinal); // cria um objeto da classe FileReader, passando o endereço do arquivo no método construtor
	    	int i; // declara o contador
	    	while ((i=fr.read()) != -1) { // while que varrerá todo o arquivo char a char
	    		Descriptografar d = new Descriptografar(); // instancia o objeto d
	    		resultado.append(d.decifrar(Character.toString ((char) i),senha)); // são adicionados a resultado caracteres um por um decodificados de acordo com a senha	  
	    	} // fim do while

	    	JOptionPane.showMessageDialog(null, "Mensagem descriptografada"); // Mensagem de sucesso
	    	JDialog.setDefaultLookAndFeelDecorated(true); // muda o estilo da caixa de diálogo a seguir
	  	    int resposta = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja mostrada?", "Confirmar",
	  	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de diálogo de YES/NO
	  	    if (resposta == JOptionPane.YES_OPTION) { // se a resposta for YES, será executado o código abaixo
	    	  JOptionPane.showMessageDialog(null, "Mensagem decodificada de " + arquivofinal + ": \n" + resultado); // mostra a mensagem decodificada
	    	  int resposta2 = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja salva?", "Confirmar",
	  	    	    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de diálogo de YES/NO
	  	    	if(resposta2 == JOptionPane.YES_OPTION) { // se a resposta for YES, será executado o código abaixo
	  	    		JFileChooserDemo pasta; //declarar variavel para receber a pasta
	  	    		pasta = new JFileChooserDemo(); // atribuindo valor
	  	          
	  	    		try { // tentar executar o código abaixo
		  	          	String escolhepasta = pasta.analisaCaminho(); // chamada ao método analiseCaminho da classe JFileChooserDemo
		  	  	        String arquivo2 = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a variável arquivo armazenará o nome do arquivo
		  	  	        
		  	  	        String x = escolhepasta.toString() + "/" + arquivo2; // a variável x faz uma concatenação entre a pasta e o arquivo a ser salvo, ficando o endereço completo
			  	  	    PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter é a classe que vai escrever no arquivo através do objeto escrever a partir do endereço do arquivo e do charset
			  	  	    escrever.println(resultado); // é escrito no arquivo x a mensagem decriptografada em resultado
			  	  	    escrever.close(); // encerra o processo de escrita, finalizando o arquivo
			  	  	    
			  	  	    JOptionPane.showMessageDialog(null, "Mensagem descriptografada salva em: " + x); // mensagem de operação concluída com sucesso
	  	    		} // fim do try
	  	    		catch (SecurityException securityException) // catch para falha na escrita
	  	    		{
	  	    			JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
		  	  	        System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
		  	  	        System.exit(1); //encerra o programa
	  	    		} // fim do catch
	  	    		catch (FileNotFoundException fileNotFoundException) // catch para erro no endereço do arquivo
	  	    		{
	  	    			JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
	  	    			System.err.println("Erro ao abrir o arquivo. Possível erro na escolha do diretório. Terminando."); // Mensagem de erro
		  	  	        System.exit(1); //encerra o programa
	  	    		} // fim do catch
	  	    	} // fim do if
	  	    } // fim do if
	  	    else { // caso condição não atendida
	  	    	int resposta3 = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja salva?", "Confirmar",
	    	    	    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de diálogo de YES/NO
	    	    	if(resposta3 == JOptionPane.YES_OPTION) { // se a resposta for YES, será executado o código abaixo
	    	    		JFileChooserDemo pasta; //declarar variavel para receber a pasta
		  	    		pasta = new JFileChooserDemo(); // atribuindo valor
		  	          
		  	    		try { // tentar executar o código abaixo
			  	          	String escolhepasta = pasta.analisaCaminho(); // chamada ao método analiseCaminho da classe JFileChooserDemo
			  	  	        String arquivo2 = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a variável arquivo armazenará o nome do arquivo
			  	  	        
			  	  	        String x = escolhepasta.toString() + "/" + arquivo2; // a variável x faz uma concatenação entre a pasta e o arquivo a ser salvo, ficando o endereço completo
				  	  	    PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter é a classe que vai escrever no arquivo através do objeto escrever a partir do endereço do arquivo e do charset
				  	  	    escrever.println(resultado); // é escrito no arquivo x a mensagem decriptografada em resultado
				  	  	    escrever.close(); // encerra o processo de escrita, finalizando o arquivo
				  	  	    
				  	  	    JOptionPane.showMessageDialog(null, "Mensagem descriptografada salva em: " + x); // mensagem de operação concluída com sucesso
		  	    		} // fim do try
		  	    		catch (SecurityException securityException) // catch para falha na escrita
		  	    		{
		  	    			JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
			  	  	        System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
			  	  	        System.exit(1); //encerra o programa
		  	    		} // fim do catch
		  	    		catch (FileNotFoundException fileNotFoundException) // catch para erro no endereço do arquivo
		  	    		{
		  	    			JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
		  	    			System.err.println("Erro ao abrir o arquivo. Possível erro na escolha do diretório. Terminando."); // Mensagem de erro
			  	  	        System.exit(1); //encerra o programa
		  	    		} // fim do catch
	    	    	} // fim do if
	  	    } // fim do else
	    } // fim do try
        catch (IOException ioException) { // catch para erro ao abrir o arquivo
        	JOptionPane.showMessageDialog(null, ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro abrindo arquivo. Terminando."); // Mensagem de erro
        } // fim do catch
    } // fim do método executaOp2
        
    private void executaOp3() throws IOException { // método da opção 3
		
    	JOptionPane.showMessageDialog(null, "Escolha o arquivo"); // exibir mensagem na interface
    	JFileChooserDemo file; //declarar variavel
        file = new JFileChooserDemo(); // atribuindo valor
        try { // tentar executar o código abaixo
	        String arquivo = file.analisaCaminho(); // cria variável arquivo
	        
	    	StringBuffer resultado= new StringBuffer(); // cria uma variável do tipo StringBuffer

	    	FileReader fr = new FileReader(arquivo); // cria um objeto da classe FileReader para ler o arquivo
	    	  int i; // cria a variável i do contador
	    	  while ((i=fr.read()) != -1) { // laço para percorrer o arquivo
	    		  resultado.append((char) i); // agrega char a char ao resultado
	    	   } // fim do while
	    	    JOptionPane.showMessageDialog(null, "Mensagem atual em " + arquivo + ": \n" + resultado); // mostra a mensagem que está no arquivo
	    	
	    	JDialog.setDefaultLookAndFeelDecorated(true); // define decoração para janela
		    int resposta = JOptionPane.showConfirmDialog(null, "Quer editar o arquivo?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de diálogo de YES/NO
		    if (resposta == JOptionPane.YES_OPTION) { // se a resposta for YES, será executado o código abaixo
		    	PrintWriter wr2 = new PrintWriter(arquivo, "UTF-8"); // PrintWriter é a classe que vai escrever no arquivo através do objeto escrever a partir do endereço do arquivo e do charset
		    	JTextArea textoArea = new JTextArea(20,20); // define uma caixa de texto de 20 x 20
		    	String y = resultado.toString(); // passa a mensagem para String
		    	textoArea.setText(y); // coloca a mensagem já armazenada no arquivo na caixa de texto textoArea
		    	JOptionPane.showMessageDialog(null, textoArea); // exibe textoArea na janela
		    	String mensagem = textoArea.getText(); // mensagem recebia o que estava na caixa de texto
		    	
		    	wr2.println(mensagem); // é escrito no arquivo o conteúdo de mensagem
		    	wr2.close(); // finaliza o arquivo
		    	JOptionPane.showMessageDialog(null, "Mensagem atualizada"); // mostra a mensagem de êxito na tela
		    } // fim do if
        } // fim do try
        catch (IOException ioException) { // catch para erro ao abrir o arquivo
        	JOptionPane.showMessageDialog(null, ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro abrindo arquivo. Terminando."); // exibe mensagem de erro
        } // fim do catch
    	
    } // fim do método executaOp3
} // fim da classe