package trabalhoCifra; // pacote ao qual o arquivo pertence

public class Criptografar { // declaração da classe Criptografar
	
	public static StringBuffer cifrar(String texto, int s) // método cifrar
    { 
        StringBuffer resultado= new StringBuffer(); // resultado é decladado como um StringBuffer
        char ch, x, y; // declaração de variáveis
  
        for (int i=0; i<texto.length(); i++) // estrutura de repetição 
        { 
        	
        	if (texto.charAt(i) >= 65 && texto.charAt(i) <= 90 ) // análise das letras maiúsculas da tabela ASCII
            { 
        		if ((texto.charAt(i) + s) >  116) { // avalia a condição de senha com valor elevado
    				int a = s % 26; // calcula o resto da divisao
    				ch = (char) (64 + a); // armazena a letra corretamente cifrada em ch
    				resultado.append(ch); // exporta o resultado
    			} // fim do if
        		else if ((texto.charAt(i) + s) > 90) { // avalia a condição se (texto.charAt(i) + s) é entre 91 e 116
        			
                    x = (char) (texto.charAt(i) + s); // passa o resultado para x
                    y = (char) (x - 90); // tira 90
                    ch = (char) (64 + y); // chega à letra corretamente cifrada
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } 
            }
        	else if (texto.charAt(i) == 32 ) { //avalia a cifragem do espaço
        		ch = '#'; // troca o espaço por #
        		resultado.append(ch); // exporta o resultado
        	} // fim do if
        	else if (texto.charAt(i) >= 97 && texto.charAt(i) <= 122) { // análise das letras minúsculas da tabela ASCII
        		if ((texto.charAt(i) + s) > 148) { // avalia a condição de senha com valor elevado
    				int a = s % 26; // calcula o resto da divisao
    				ch = (char) (96 + a); // armazena a letra corretamente cifrada em ch
    				resultado.append(ch); // exporta o resultado
    			} // fim do if
        		else if ((int) (texto.charAt(i) + s) > 122) { // avalia a condição se (texto.charAt(i) + s) é entre 123 e 148
                    x = (char) (texto.charAt(i) + s); // passa o resultado para x
                    y = (char) (x - 122); // tira 122
                    ch = (char) (96 + y); // chega à letra corretamente cifrada
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } // fim do else
        	} // fim do if
        	else {
        		ch = texto.charAt(i); // armazena os caracteres não mapeados anteriormente em sua forma natural
        		resultado.append(ch); // exporta o resultado
        	} // fim do else
        		
        }    
        return resultado; // retorna a variável resultado
    } 
} // fim da classe

