package trabalhoCifra; // pacote ao qual o arquivo pertence

public class Criptografar { // declara��o da classe Criptografar
	
	public static StringBuffer cifrar(String texto, int s) // m�todo cifrar
    { 
        StringBuffer resultado= new StringBuffer(); // resultado � decladado como um StringBuffer
        char ch, x, y; // declara��o de vari�veis
  
        for (int i=0; i<texto.length(); i++) // estrutura de repeti��o 
        { 
        	
        	if (texto.charAt(i) >= 65 && texto.charAt(i) <= 90 ) // an�lise das letras mai�sculas da tabela ASCII
            { 
        		if ((texto.charAt(i) + s) >  116) { // avalia a condi��o de senha com valor elevado
    				int a = s % 26; // calcula o resto da divisao
    				ch = (char) (64 + a); // armazena a letra corretamente cifrada em ch
    				resultado.append(ch); // exporta o resultado
    			} // fim do if
        		else if ((texto.charAt(i) + s) > 90) { // avalia a condi��o se (texto.charAt(i) + s) � entre 91 e 116
        			
                    x = (char) (texto.charAt(i) + s); // passa o resultado para x
                    y = (char) (x - 90); // tira 90
                    ch = (char) (64 + y); // chega � letra corretamente cifrada
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } 
            }
        	else if (texto.charAt(i) == 32 ) { //avalia a cifragem do espa�o
        		ch = '#'; // troca o espa�o por #
        		resultado.append(ch); // exporta o resultado
        	} // fim do if
        	else if (texto.charAt(i) >= 97 && texto.charAt(i) <= 122) { // an�lise das letras min�sculas da tabela ASCII
        		if ((texto.charAt(i) + s) > 148) { // avalia a condi��o de senha com valor elevado
    				int a = s % 26; // calcula o resto da divisao
    				ch = (char) (96 + a); // armazena a letra corretamente cifrada em ch
    				resultado.append(ch); // exporta o resultado
    			} // fim do if
        		else if ((int) (texto.charAt(i) + s) > 122) { // avalia a condi��o se (texto.charAt(i) + s) � entre 123 e 148
                    x = (char) (texto.charAt(i) + s); // passa o resultado para x
                    y = (char) (x - 122); // tira 122
                    ch = (char) (96 + y); // chega � letra corretamente cifrada
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } // fim do else
        	} // fim do if
        	else {
        		ch = texto.charAt(i); // armazena os caracteres n�o mapeados anteriormente em sua forma natural
        		resultado.append(ch); // exporta o resultado
        	} // fim do else
        		
        }    
        return resultado; // retorna a vari�vel resultado
    } 
} // fim da classe

