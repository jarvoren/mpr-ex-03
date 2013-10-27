package mpr.exs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
/*
 * Korzystając z mechanizmów kolekcji (w tym z odwzorowań/map) zmodyfikuj
 * poniższy kod tak, żeby na standardowym wyjściu produkować „indeks wystapień
 * słów” w załączonym tekście „Księcia” Machiavellego. Indeks powinien składać się 
 * z linii postaci:
 * 
 * <słowo><tabulacja><w1>, <w2>, <w3>, ..., <wk>
 * 
 * gdzie:
 *   - <słowo> oznacza dowolne słowo wystepujące w tekście
 *   - <tabulacja> oznacza znak tabulacji
 *   - <w1>,...,<wk> oznaczają numery wierszy w tekście, w których słowo występuje
 *   
 * Dodatkowe założenia:
 * 
 *   - Sporządzając indeks pomijamy różnice pomiędzy wielkimi i małymi literami.
 *   - Indeks powinien być posortowany alfabetycznie.
 *   - Słowa w indeksie powinny występować bez powtórzeń.
 *   - Numery wierszy (w rwmach pojedynczego wpisu) powinny być uporządkowane rosnąco (bez powtórzeń).
 * 
 */
public class Ex03 {

	public static void main(String[] args) {
		String fname = "Machiavelli.txt";
		try {
			
			Map<String, Queue<Integer>> mapa = new TreeMap<String , Queue<Integer>>();
			int lineIterator=1;
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = br.readLine();
			
			while (line != null) {
				lineIterator++; 
				// oczyszczamy tekst ze znaków interpunkcyjnych, liczb itp.
				line = line.replaceAll("\\d+|[:,\\.\"\\?!;\\-/]|\\b[XIV]+\\b", " ");
				// usuwamy ewentualne odstępy na początku i na końcu linii
				line = line.replaceAll("^\\s+|\\s+$", "");
				if (!line.matches("^\\s*$")) {
					String[] words = line.split("\\s+");
					for (String w : words) {
						if(mapa.containsKey(w))
						{
							mapa.get(w).add(lineIterator);
						}
						else
						{
							
							Queue<Integer> lista = new PriorityQueue<Integer>();
							mapa.put(w, lista);
							mapa.get(w).add(lineIterator);
						}
					}
					System.out.println();
				}
				line = br.readLine();
			}
			br.close();
			
			
			
			for(String s : mapa.keySet())
			{
				
				if(s.length()>=8)
				{
					
					System.out.println(s+"\t"+mapa.get(s));
				}
				else
				{
					System.out.println(s+"\t\t"+mapa.get(s));
				}
			}
				
		} catch (FileNotFoundException e) {
			System.out.println("Nie mogę otworzyć pliku " + fname);
		} catch (IOException e) {
			System.out.println("Błąd podczas czytania z pliku" + fname);
		}
		
		
	}

}
