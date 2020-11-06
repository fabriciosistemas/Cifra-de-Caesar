package trabalhoCifra; // pacote ao qual o arquivo pertence
import java.io.FileNotFoundException; // importa��o de FileNotFoundException
import java.io.FileReader; // importa��o de FileReader
import java.io.IOException; // importa��o de IOException
import java.io.PrintWriter; // importa��o de PrintWriter
import javax.swing.JDialog; // importa��o de JDialog
import javax.swing.JOptionPane; // importa��o de JOptionPane
import javax.swing.JTextArea; // importa��o de JTextArea

public class Menu { // Cria��o de classe Menu
    String lista = "1 � Cifrar Mensagem e Exportar txt\n2 � Decifrar mensagem a partir de arquivo\n3 � Visualizar e Editar anota��o\nS � SAIR"; // cria��o de itens do Menu

    // M�todo que exibe o menu usando JOptionPane.showInputDialog

    public String mostrarMenu() { // M�todo que exibe o menu usando JOptionPane.showInputDialog

        return JOptionPane.showInputDialog(null, lista); // Chamada para o JOptionPane

    } // fim do m�todo mostrarMenu

    public void executaMenu() throws IOException { // M�todo de execu��o do Menu

        String opcao = ""; // Inicializa a vari�vel opcao

        while (!opcao.equalsIgnoreCase("s")) { // enquanto op��o for diferente de S ou s, fica no loop de exibi��o do Menu

            opcao = mostrarMenu(); // Chamada para o m�todo mostrarMenu

            switch (opcao) { // Inicializa��o do switch/case

                case "1": // caso o valor inserido seja 1, executa o c�digo abaixo
                	
                    executaOp1(); // Chamada ao m�todo executaOp��o1
                    
                    break; // sai do switch e volta a avaliar a condi��o do while

                case "2": // caso o valor inserido seja 2, executa o c�digo abaixo

                    executaOp2(); // Chamada ao m�todo executaOp��o2

                    break; // sai do switch e volta a avaliar a condi��o do while

                case "3": // caso o valor inserido seja 3, executa o c�digo abaixo

                    executaOp3(); // Chamada ao m�todo executaOp��o3

                    break; // sai do switch e volta a avaliar a condi��o do while

                case "S": // caso o valor inserido seja S, executa o c�digo do case "s"

                case "s": // caso o valor inserido seja s, executa o c�digo abaixo
                    JOptionPane.showMessageDialog(null, "At� logo!"); // Exibe uma caixa de texto com a mensagem "Adeus"

                    break; // sai do switch e volta a avaliar a condi��o do while

                default: // O que ser� feito caso o entrado n�o tenha sido um dos predefinidos acima

                    JOptionPane.showMessageDialog(null, "N�o entendi sua escolha\nVou exibir o menu novamente"); // Ser� mostrada uma mensagem e voltar� ao Menu Principal

            } // fim do switch

        } // fim do while

    } // fim do m�todo executaMenu

    private void executaOp1() throws IOException { // m�todo da op��o 1
    	JOptionPane.showMessageDialog(null, "Escolha a pasta"); // JOptionPane para exibi��o da mensagem na tela
    	JFileChooserDemo pasta; //declarar variavel para receber a pasta
        pasta = new JFileChooserDemo(); // atribuindo valor
        
        try { // tentar executar o c�digo abaixo
        	String escolhepasta = pasta.analisaCaminho(); // chamada ao m�todo analiseCaminho da classe JFileChooserDemo
	        String arquivo = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a vari�vel arquivo armazenar� o nome do arquivo
	        
	        String x = escolhepasta.toString() + "/" + arquivo; // a vari�vel x faz uma concatena��o entre a pasta e o arquivo a ser salvo, ficando o endere�o completo
	         
	    	PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter � a classe que vai escrever no arquivo atrav�s do objeto escrever a partir do endere�o do arquivo e do charset
	    	
	    	JTextArea textoArea = new JTextArea(20,20); // define uma caixa de texto de 20 x 20
	    	JOptionPane.showMessageDialog(null, textoArea,"Entre com a mensagem desejada",JOptionPane.OK_CANCEL_OPTION); // Abre o local de entrada de texto
	    	String mensagem = textoArea.getText(); // a vari�vel mensagem recebe o texto digitado na �rea de texto
	    	
	        int senha = Integer.parseInt(JOptionPane.showInputDialog("Entre com a senha")); // a vari�vel senha vai armazenar a chave de criptografia da mensagem
	        
	        Criptografar c = new Criptografar(); // instancia o objeto c
	        escrever.println(c.cifrar(mensagem, senha)); // � escrito no arquivo x a mensagem criptografada a partir da senha
	        escrever.close(); // encerra o processo de escrita, finalizando o arquivo
	        
	        JOptionPane.showMessageDialog(null, "Mensagem criptografada em: " + x); // mensagem final da opera��o, indicando onde o arquivo foi salvo

        } // fim do try
        catch (SecurityException securityException) // catch para falha na escrita
        {
        	JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
	        System.exit(1); //encerra o programa
        } // fim do catch
        catch (FileNotFoundException fileNotFoundException) // catch para erro no endere�o do arquivo
        {
        	JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro ao criar o arquivo. Poss�vel erro na escolha do diret�rio. Terminando."); // Mensagem de erro
	        System.exit(1); //encerra o programa
        } // fim do catch
    } // fim do m�todo executaOp1

    private void executaOp2() throws IOException { // m�todo da op��o 2
    	JOptionPane.showMessageDialog(null, "Escolha o arquivo"); // JOptionPane que mostra a mensagem "Escolha o arquivo" na tela
    	JFileChooserDemo arquivo; //declarar variavel
    	arquivo = new JFileChooserDemo(); // atribuindo valor
        try { // tentar executar o c�digo abaixo
	        String arquivofinal = arquivo.analisaCaminho(); // chamada ao m�todo analiseCaminho da classe JFileChooserDemo
	        
	    	int senha = Integer.parseInt(JOptionPane.showInputDialog("Entre com a senha:")); // a vari�vel senha vai armazenar a chave de criptografia da mensagem
	    	StringBuffer resultado= new StringBuffer(); // cria o objeto result de StringBuffer
	    	FileReader fr = new FileReader(arquivofinal); // cria um objeto da classe FileReader, passando o endere�o do arquivo no m�todo construtor
	    	int i; // declara o contador
	    	while ((i=fr.read()) != -1) { // while que varrer� todo o arquivo char a char
	    		Descriptografar d = new Descriptografar(); // instancia o objeto d
	    		resultado.append(d.decifrar(Character.toString ((char) i),senha)); // s�o adicionados a resultado caracteres um por um decodificados de acordo com a senha	  
	    	} // fim do while

	    	JOptionPane.showMessageDialog(null, "Mensagem descriptografada"); // Mensagem de sucesso
	    	JDialog.setDefaultLookAndFeelDecorated(true); // muda o estilo da caixa de di�logo a seguir
	  	    int resposta = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja mostrada?", "Confirmar",
	  	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de di�logo de YES/NO
	  	    if (resposta == JOptionPane.YES_OPTION) { // se a resposta for YES, ser� executado o c�digo abaixo
	    	  JOptionPane.showMessageDialog(null, "Mensagem decodificada de " + arquivofinal + ": \n" + resultado); // mostra a mensagem decodificada
	    	  int resposta2 = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja salva?", "Confirmar",
	  	    	    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de di�logo de YES/NO
	  	    	if(resposta2 == JOptionPane.YES_OPTION) { // se a resposta for YES, ser� executado o c�digo abaixo
	  	    		JFileChooserDemo pasta; //declarar variavel para receber a pasta
	  	    		pasta = new JFileChooserDemo(); // atribuindo valor
	  	          
	  	    		try { // tentar executar o c�digo abaixo
		  	          	String escolhepasta = pasta.analisaCaminho(); // chamada ao m�todo analiseCaminho da classe JFileChooserDemo
		  	  	        String arquivo2 = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a vari�vel arquivo armazenar� o nome do arquivo
		  	  	        
		  	  	        String x = escolhepasta.toString() + "/" + arquivo2; // a vari�vel x faz uma concatena��o entre a pasta e o arquivo a ser salvo, ficando o endere�o completo
			  	  	    PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter � a classe que vai escrever no arquivo atrav�s do objeto escrever a partir do endere�o do arquivo e do charset
			  	  	    escrever.println(resultado); // � escrito no arquivo x a mensagem decriptografada em resultado
			  	  	    escrever.close(); // encerra o processo de escrita, finalizando o arquivo
			  	  	    
			  	  	    JOptionPane.showMessageDialog(null, "Mensagem descriptografada salva em: " + x); // mensagem de opera��o conclu�da com sucesso
	  	    		} // fim do try
	  	    		catch (SecurityException securityException) // catch para falha na escrita
	  	    		{
	  	    			JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
		  	  	        System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
		  	  	        System.exit(1); //encerra o programa
	  	    		} // fim do catch
	  	    		catch (FileNotFoundException fileNotFoundException) // catch para erro no endere�o do arquivo
	  	    		{
	  	    			JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
	  	    			System.err.println("Erro ao abrir o arquivo. Poss�vel erro na escolha do diret�rio. Terminando."); // Mensagem de erro
		  	  	        System.exit(1); //encerra o programa
	  	    		} // fim do catch
	  	    	} // fim do if
	  	    } // fim do if
	  	    else { // caso condi��o n�o atendida
	  	    	int resposta3 = JOptionPane.showConfirmDialog(null, "Quer que a mensagem seja salva?", "Confirmar",
	    	    	    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de di�logo de YES/NO
	    	    	if(resposta3 == JOptionPane.YES_OPTION) { // se a resposta for YES, ser� executado o c�digo abaixo
	    	    		JFileChooserDemo pasta; //declarar variavel para receber a pasta
		  	    		pasta = new JFileChooserDemo(); // atribuindo valor
		  	          
		  	    		try { // tentar executar o c�digo abaixo
			  	          	String escolhepasta = pasta.analisaCaminho(); // chamada ao m�todo analiseCaminho da classe JFileChooserDemo
			  	  	        String arquivo2 = JOptionPane.showInputDialog("Entre com o nome do arquivo (Formato arquivo.txt)"); // a vari�vel arquivo armazenar� o nome do arquivo
			  	  	        
			  	  	        String x = escolhepasta.toString() + "/" + arquivo2; // a vari�vel x faz uma concatena��o entre a pasta e o arquivo a ser salvo, ficando o endere�o completo
				  	  	    PrintWriter escrever = new PrintWriter(x, "UTF-8"); // PrintWriter � a classe que vai escrever no arquivo atrav�s do objeto escrever a partir do endere�o do arquivo e do charset
				  	  	    escrever.println(resultado); // � escrito no arquivo x a mensagem decriptografada em resultado
				  	  	    escrever.close(); // encerra o processo de escrita, finalizando o arquivo
				  	  	    
				  	  	    JOptionPane.showMessageDialog(null, "Mensagem descriptografada salva em: " + x); // mensagem de opera��o conclu�da com sucesso
		  	    		} // fim do try
		  	    		catch (SecurityException securityException) // catch para falha na escrita
		  	    		{
		  	    			JOptionPane.showMessageDialog(null, securityException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
			  	  	        System.err.println("Permissao de escrita negada. Terminando."); // Mensagem de erro
			  	  	        System.exit(1); //encerra o programa
		  	    		} // fim do catch
		  	    		catch (FileNotFoundException fileNotFoundException) // catch para erro no endere�o do arquivo
		  	    		{
		  	    			JOptionPane.showMessageDialog(null, fileNotFoundException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
		  	    			System.err.println("Erro ao abrir o arquivo. Poss�vel erro na escolha do diret�rio. Terminando."); // Mensagem de erro
			  	  	        System.exit(1); //encerra o programa
		  	    		} // fim do catch
	    	    	} // fim do if
	  	    } // fim do else
	    } // fim do try
        catch (IOException ioException) { // catch para erro ao abrir o arquivo
        	JOptionPane.showMessageDialog(null, ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro abrindo arquivo. Terminando."); // Mensagem de erro
        } // fim do catch
    } // fim do m�todo executaOp2
        
    private void executaOp3() throws IOException { // m�todo da op��o 3
		
    	JOptionPane.showMessageDialog(null, "Escolha o arquivo"); // exibir mensagem na interface
    	JFileChooserDemo file; //declarar variavel
        file = new JFileChooserDemo(); // atribuindo valor
        try { // tentar executar o c�digo abaixo
	        String arquivo = file.analisaCaminho(); // cria vari�vel arquivo
	        
	    	StringBuffer resultado= new StringBuffer(); // cria uma vari�vel do tipo StringBuffer

	    	FileReader fr = new FileReader(arquivo); // cria um objeto da classe FileReader para ler o arquivo
	    	  int i; // cria a vari�vel i do contador
	    	  while ((i=fr.read()) != -1) { // la�o para percorrer o arquivo
	    		  resultado.append((char) i); // agrega char a char ao resultado
	    	   } // fim do while
	    	    JOptionPane.showMessageDialog(null, "Mensagem atual em " + arquivo + ": \n" + resultado); // mostra a mensagem que est� no arquivo
	    	
	    	JDialog.setDefaultLookAndFeelDecorated(true); // define decora��o para janela
		    int resposta = JOptionPane.showConfirmDialog(null, "Quer editar o arquivo?", "Confirmar",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // Uma caixa de di�logo de YES/NO
		    if (resposta == JOptionPane.YES_OPTION) { // se a resposta for YES, ser� executado o c�digo abaixo
		    	PrintWriter wr2 = new PrintWriter(arquivo, "UTF-8"); // PrintWriter � a classe que vai escrever no arquivo atrav�s do objeto escrever a partir do endere�o do arquivo e do charset
		    	JTextArea textoArea = new JTextArea(20,20); // define uma caixa de texto de 20 x 20
		    	String y = resultado.toString(); // passa a mensagem para String
		    	textoArea.setText(y); // coloca a mensagem j� armazenada no arquivo na caixa de texto textoArea
		    	JOptionPane.showMessageDialog(null, textoArea); // exibe textoArea na janela
		    	String mensagem = textoArea.getText(); // mensagem recebia o que estava na caixa de texto
		    	
		    	wr2.println(mensagem); // � escrito no arquivo o conte�do de mensagem
		    	wr2.close(); // finaliza o arquivo
		    	JOptionPane.showMessageDialog(null, "Mensagem atualizada"); // mostra a mensagem de �xito na tela
		    } // fim do if
        } // fim do try
        catch (IOException ioException) { // catch para erro ao abrir o arquivo
        	JOptionPane.showMessageDialog(null, ioException.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // mostra mensagem de erro na interface
        	System.err.println("Erro abrindo arquivo. Terminando."); // exibe mensagem de erro
        } // fim do catch
    	
    } // fim do m�todo executaOp3
} // fim da classe