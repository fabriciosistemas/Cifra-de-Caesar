package trabalhoCifra; // pacote ao qual o arquivo pertence

public class Descriptografar { // declaração da classe Descriptografar
	public static StringBuffer decifrar(String texto, int s)  // método decifrar
    { 
        int valor = s * (-1);
		StringBuffer resultado = new StringBuffer(); // resultado é decladado como um StringBuffer
		char ch; // declaração de variáveis
  
        for (int i=0; i<texto.length(); i++) // estrutura de repetição
        { 
        	if (texto.charAt(i) >= 65 && texto.charAt(i) <= 90 ) // análise das letras maiúsculas da tabela ASCII
            { 
        		//if (texto.charAt(i) + valor > 90) {
        		if( texto.charAt(i) <= (s + 64) ) { // avalia uma condição
                    ch = (char) (26 + texto.charAt(i) - s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + valor); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                    System.out.println("teste"); 
                }
            }
        	else if (texto.charAt(i) == 35 ) { // decifra o sinal #
        		ch = ' ';
        		resultado.append(ch); // exporta o resultado
        	}
        	else if (texto.charAt(i) >= 97 && texto.charAt(i) <= 122) { // análise das letras minúsculas da tabela ASCII
        		//if ((int) (texto.charAt(i) + valor) > 122) {
        		if( texto.charAt(i) <= ( s + 96 ) ) { // avalia uma condição
                    ch = (char) (26 + texto.charAt(i) - s); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                } else {
                    ch = (char) (texto.charAt(i) + valor); // armazena a letra corretamente cifrada em ch
                    resultado.append(ch); // exporta o resultado
                }
        	}
        	else {
        		ch = texto.charAt(i); // decodifica os caracteres codificados em sua forma natural
        		resultado.append(ch); // exporta o resultado
        	}
        		
        		//char ch = (char)(int)text.charAt(i);
                //if (ch != ' ') {
        		/*char ch = (char)(((int)text.charAt(i) + 
                                  s - 65) % 26 + 65); //} else {
                                  	//ch = ' ';*/
            //result.append(ch); 
        }
            
            /*else
            { 
                char ch = (char)(((int)text.charAt(i) + 
                                  s - 97) % 26 + 97); 
                result.append(ch); 
            } 
        } */
        return resultado; // retorna a variável resultado
        	
        	
        	
        	
        	/*if (Character.isUpperCase(text.charAt(i))) 
            { 
                char ch = (char)(((int)text.charAt(i) + 
                			valor - 65) % 26 + 65); 
                result.append(ch); 
            } 
            else
            { 
                char ch = (char)(((int)text.charAt(i) + 
                			valor - 97) % 26 + 97); 
                result.append(ch); 
            } 
        } 
        return result; */
    } 
}
