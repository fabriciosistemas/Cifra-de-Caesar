package trabalhoCifra; // pacote ao qual o arquivo pertence
import java.io.IOException; // importação de IOException
import java.nio.file.Path; // importação de Path
import javax.swing.JFileChooser; // importação de JFileChooser
import javax.swing.JFrame; // importação de JFrame
import javax.swing.JOptionPane; // importação de JOptionPane
import javax.swing.JTextArea; // importação de JTextArea

public class JFileChooserDemo extends JFrame // a classe JFileChooserDemo herda de JFrame
{
   private JTextArea areaDeTexto; // exibe conteudos de arquivo 
   
   
   public JFileChooserDemo() throws IOException // metodo construtor 
   {
      super("Demonstracao do JFileChooser");//aciona o contrustor da superclasse
      this.areaDeTexto = new JTextArea(); // instancia areaDeTexto
      //this.add(areaDeTexto);
      /*this.add(new JScrollPane(areaDeTexto)); // area de exibicao vai ter uma barra de rolagem
      setSize(400, 400); 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true); */
      //analisaCaminho(); // pega o caminho do usuario e exibe informacao
   } 

   
   public String analisaCaminho() throws IOException // analise o arquivo ou diretorio informado pelo usuario 
   {
      
      Path caminho = pegaCaminhoDeArquivoOuDiretorio(); // pega caminho do usuario
      String x = caminho.toString(); // passa caminho para String

      JOptionPane.showMessageDialog(null, "Caminho inserido"); // mensagem de ok
      return x; // retorna x
      /*if (caminho != null && Files.exists(caminho)) // se existir exibe informacoes
      {
         // pega informacoes sobre arquivo ou diretorio
         StringBuilder criadorDeString = new StringBuilder();
         
         String nomeDoArquivo=caminho.getFileName().toString();
         criadorDeString.append(String.format("%s:%n", nomeDoArquivo));
         
      	criadorDeString.append(String.format("%s um diretorio%n", 
      		Files.isDirectory(caminho) ? "eh" : "nao eh"));
      	
      	criadorDeString.append(String.format("%s um caminho absoluto%n", 
      		caminho.isAbsolute() ? "eh" : "nao eh"));
      	criadorDeString.append(String.format("modificado pela ultima vez em: %s%n", 
      		Files.getLastModifiedTime(caminho)));
      	criadorDeString.append(String.format("Tamanho: %s%n", Files.size(caminho)));
      	criadorDeString.append(String.format("Caminho: %s%n", caminho));
      	criadorDeString.append(String.format("Caminho absoluto: %s%n", 
            caminho.toAbsolutePath()));

         if (Files.isDirectory(caminho)) // se diretorio, pega lista do diretorio
         {
            criadorDeString.append(String.format("%nConteudo do diretorio:%n"));
            
            // objeto para iterar sobre o conteudo do diretorio
            // stream fluxo
            DirectoryStream<Path> fluxoDeDiretorio = 
               Files.newDirectoryStream(caminho);
   
            for (Path p : fluxoDeDiretorio)
               criadorDeString.append(String.format("%s%n", p));
         }

         areaDeTexto.setText(criadorDeString.toString()); // exibe conteudo da String de informacoes
      } 
      else // se caminho nao existe
      {
         JOptionPane.showMessageDialog(this, caminho.getFileName() +
            " nao existe.", "ERROR", JOptionPane.ERROR_MESSAGE);
      }   */
   } // fim do metodo analisaCaminho

   
   private Path pegaCaminhoDeArquivoOuDiretorio() // pede o caminho
   {
      JFileChooser escolhedorDeCaminho = new JFileChooser();  // instancia escolhedorDeCaminho
      
      escolhedorDeCaminho.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // dialogo para selecionar arquivo ou diretorio
      
      int resultado = escolhedorDeCaminho.showOpenDialog(this); // mostra opções de escolha como cancelar, por exemplo

      if (resultado == JFileChooser.CANCEL_OPTION) // if para avaliar se o usuário clicou em cancelar
         System.exit(1); // fecha o programa

      return escolhedorDeCaminho.getSelectedFile().toPath(); //retorna o caminho
   } 
} // fim da classe JFileChooserDemo

/*************************************************************************
* (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
* Pearson Education, Inc. All Rights Reserved.                           *
*                                                                        *
* DISCLAIMER: The authors and publisher of this book have used their     *
* best efforts in preparing the book. These efforts include the          *
* development, research, and testing of the theories and programs        *
* to determine their effectiveness. The authors and publisher make       *
* no warranty of any kind, expressed or implied, with regard to these    *
* programs or to the documentation contained in these books. The authors *
* and publisher shall not be liable in any event for incidental or       *
* consequential damages in connection with, or arising out of, the       *
* furnishing, performance, or use of these programs.                     *
*************************************************************************/