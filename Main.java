import java.util.ArrayList;
import java.util.HashMap;

class Main {
  public static void main(String[] args) {
    /*
    Flag = false
    Setup 
    While(Flag != true) {
      Check
      Select
      Crossover
      Mutate
    }
    */
    ArrayList<String> population = setup();
    boolean flag = true;
    int gen = 1;
  
    while (gen != 10) {
      for(int i=0;i<population.size();i++) {
        if (population.get(i).equals(Global.getTarget())) {
          flag = true;
        } 
      }

      population = fitness(population);
      System.out.println(population);
      population = crossover(population);
      System.out.println("Generation: " + gen);
      System.out.println(population);
      gen++;
    }
  

    //fitness(population);

  }

  public static ArrayList<String> setup() {
    String alphabet = "abcdefghijklmnopqrstuvwxyz ";
    ArrayList<String> pop = new ArrayList<>();
    String newWord;
    int index;
    
    for(int i=0;i<Global.getPopulationSize();i++) {
      newWord = "";
      for(int j=0;j<Global.getTarget().length();j++) {
        index = (int) (Math.random() * 27);
        newWord += alphabet.substring(index,index+1);
      }
      pop.add(newWord);
    }
    return pop;
  }

  public static ArrayList<String> fitness(ArrayList<String> population) {
    double fit;
    double[] arr = new double[population.size()];

    for(int i=0;i<population.size();i++) {
      fit = 0.0;
      for(int j=0;j<population.get(i).length();j++) {
        if (population.get(i).substring(j,j+1).equals(Global.getTarget().substring(j,j+1))) {
        fit += (double) 1/Global.getTarget().length() * 100;
        }
      arr[i] = fit;
      }
    }
    return newPop(population, arr);
  }

  public static ArrayList<String> newPop(ArrayList<String> population, double[] populationFitness) {
    ArrayList<String> newPop = new ArrayList<String>();
    double probablity;

    for(int i=0;i<population.size();i++) {
      probablity = Math.random() * 100;
      if(probablity < populationFitness[i]) {
        newPop.add(population.get(i));
      }
    }
    return newPop;
  }

  public static ArrayList<String> crossover(ArrayList<String> mostFit) {
    ArrayList<String> newPop = new ArrayList<>();
    String str1;
    String str2;
    String newStr = "";

    for(int i=0;i<mostFit.size()/2; i++) {
      newStr = "";
      str1 = mostFit.remove(0);
      str2 = mostFit.remove(0);
      for(int j=0; j<str1.length();j++) {
        if(((int) (Math.random() * 2) == 1)) {
          newStr += str1.substring(j,j+1);
        } else {
          newStr += str2.substring(j,j+1);
        }
      }
      newPop.add(newStr);
    }
    if(newPop.isEmpty() != true) {
      newPop.add(mostFit.remove(0));
    }
    return newPop;
  }
}
