import java.util.ArrayList;
import java.util.HashMap;

class Main {
  public static void main(String[] args) {

    String[] population = setup();
    double[] populationFitness = fitness(population);

    ArrayList<String> mostFit = newPop(population, populationFitness);

    ArrayList<String> newPop = crossover(mostFit);
    System.out.println(population);
    System.out.println(newPop);

    /*
    for(int i=0; i<populationFitness.length;i++) {
      System.out.println(populationFitness[i]);
    }
    HashMap<Integer, Integer> map = new HashMap(5);
    map.put(2,4);
    System.out.println(map.get(2));
    */
  }

  public static String[] setup() {
    String alphabet = "abcdefghijklmnopqrstuvwxyz ";
    String[] population = new String[Global.getPopulationSize()];
    int index;
    
    for(int i=0;i<Global.getPopulationSize();i++) {
        population[i] = "";
      for(int j=0;j<Global.getTarget().length();j++) {
        index = (int) (Math.random() * 27);
        population[i] += alphabet.substring(index,index+1);
      }
    }
    return population;
  }

  public static double[] fitness(String[] population) {
    double fit;
    double[] arr = new double[population.length];

    for(int i=0;i<population.length;i++) {
      fit = 0.0;
      for(int j=0;j<population[i].length();j++) {
      if (population[i].substring(j,j+1).equals(Global.getTarget().substring(j,j+1))) {
        fit += (double) 1/Global.getTarget().length() * 100;
      }
      arr[i] = fit;
    }
  }
    return arr;
}

  public static ArrayList<String> newPop(String[] population, double[] populationFitness) {
    ArrayList<String> newPop = new ArrayList<String>();
    double probablity;

    for(int i=0;i<population.length;i++) {
      probablity = Math.random() * 100;
      if(probablity < populationFitness[i]) {
        newPop.add(population[i]);
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
