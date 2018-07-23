import java.util.*;
import java.io.*;

public class DotComBust {
	private GameHelper helper = new GameHelper(); // создается объект GameHelper, предназначенный для получения координат сайта
	private ArrayList<DotCom> dotComList = new ArrayList<DotCom>(); // создается список объектов dotComList для хранения сайтов 
	private int numOfGuesses = 0; // объявляется переменная объекта DotComBust, считающая количество попыток

	private void setUpGame() { // создается метод setUpGame, отвечающий за подготовку игры
		DotCom one = new DotCom(); // создание объектов(сайтов)
		one.setName("pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("go2.com");
		dotComList.add(one); // добавление сайтов в dotComList
		dotComList.add(two);
		dotComList.add(three);

		System.out.println("Ваша цель - потопить три сайта."); // инструкции для пользователя перед началом игры
		System.out.println("pets.com, eToys.com, go2.com");
		System.out.println("Попытайтесь потопить их за минимальное количество ходов"); 

		    for (DotCom dotComtoSet : dotComList ) { //просматриваем все элементы массива dotComList
			    ArrayList<String> newLocation = helper.placeDotCom(3); // отправляем запрос в GameHelper, просим передать адреса трёх ячеек сайта, получаем их
			    dotComtoSet.setLocationCells(newLocation); // передаем полученные координаты методу setLocationCells
		    }
    }

	private void startPlaying() { // создается метод startPlaying
		while (!dotComList.isEmpty()) { // выполняется цикл до тех пор пока список dotComList не будет пустым
			String userG = helper.getUserInput("Сделайте ход"); // получаем пользовательский ввод
			checkUserGuess(userG); // передаем пользовательский ввод методу checkUserGuess
		}
		finishGame();// вызываем метод finishGame
	}

	private void checkUserGuess(String userGuess) { // создаем метод checkUserGuess
		numOfGuesses++; // считаем количество попыток
		String result = "Мимо";
		for (DotCom dotComtoTest : dotComList ) { // проверяем все объекты
			result = dotComtoTest.checkYourself(userGuess); //передаем ход userGuess и получаем результат от метода checkYourself
			if (result.equals("Попал")) {
				break;
			}
			if (result.equals("Потопил")) { 
				dotComList.remove(dotComtoTest);
				break;
			}	
		}
		System.out.println(result); // выводим результат
	}

	private void finishGame() { // создаем метод finishGame и выводим итоги
		System.out.println("Все сайты ушли ко дну. Ваши акции теперь ничего не стоят. ");
		if (numOfGuesses <= 18) {
		System.out.println("'Это заняло у вас всего" + numOfGuesses + " попыток. ");
		System.out.println("Вы успели выбраться до того, как ваши вложения утонули. ");
	    }
	    else {
		System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses + " попыток. ");
		System.out.println("Рыбы ходят хороводы вокруг ваших вложений.");
	    }
	}
	
	public static void main(String[] args) { // создаем главный метод 
		DotComBust game = new DotComBust(); // создаем объект DotComBust(игру)
		game.setUpGame(); //вызываем методы setUpGame и startPlaying
		game.startPlaying();
	}

}	
	class DotCom {
	ArrayList<String> locationCells; //создаем массив для хранения адресов сайтов
	String name; // переменная с именем сайта
	public void setLocationCells(ArrayList<String> loc) { // метод принимает массив с адресом сайта
	locationCells = loc; // записываем адрес сайта в массив locationCells
    }

    public void setName(String n) { //создаем метод setName, дающий сайтам названия
    name = n;
    }

    public String checkYourself(String userInput) { // создаем метод checkYourself, который получает ход пользователя и проверяет его
	    String result = "Мимо"; 
	    int index = locationCells.indexOf(userInput); // используем метод indexOf, проверяющий, был ли такой ход
	    if (index >= 0) {
	    	locationCells.remove(index);// если такой ход был удаляем элемент массива
	        if (locationCells.isEmpty()) { // если массив становится пустым, мы потопили сайт
	        	result = "Потопил"; 
	        	System.out.println("Ой, вы потопили " + name + " :( ");
	        } else {    		
			    result = "Попал";
		    }
	    } 
	    return result;	       
	}
}

class GameHelper	{ // генерируем адреса сайтов и передаем пользовательский ввод
	private static final String alphabet = "abcdefg";
	private int gridLength = 7;
    private int gridSize = 49;
	private int[] grid = new int [gridSize];
	private int comCount = 0;

	public String getUserInput(String promt) {
		String inputLine = null;
		System.out.println( promt + " ");
		try {
			BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
				inputLine = is.readLine();
				if (inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println( "IOException: " + e);
		}
		return inputLine.toLowerCase();
	}
	public ArrayList<String> placeDotCom( int comSize ) {
		ArrayList<String> alphaCells = new ArrayList<String>();
		String [] alphacoords = new String[comSize];
		String temp = null;
		int [] coords = new int[comSize];
		int attempts = 0;
		boolean success = false;
		int location = 0;

		comCount++;
		int incr = 1;
		if ((comCount % 2) == 1) {
			incr = gridLength;
		}
		
		while (!success & attempts++ < 200) { 
			location = (int) (Math.random() * gridSize);
		    int x = 0;
		    success = true; 
		    while (success && x < comSize) { 
		    	if (grid[location] == 0) { 
		    		coords[x++] = location;
			        location += incr;
			    if (location >= gridSize) {
				   success = false;
			    }
			    if (x > 0 && (location % gridLength == 0)) {
				success = false;
			    }
			    }  else { 
			    	success = false;
			       }	
			}
		}

		int x = 0;
		int row = 0;
		int column = 0;
		while (x < comSize) {
			grid[coords[x]] = 1;
			row = (int)(coords[x] / gridLength);
			column = coords[x] % gridLength;
            temp =String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }
        
        return alphaCells;
    }
}    

          










				
















	










	



			









	

			
























