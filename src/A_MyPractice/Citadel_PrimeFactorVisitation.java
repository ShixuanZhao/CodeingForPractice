import java.util.HashSet;

public class Citadel_PrimeFactorVisitation {
    public static void main(String[] args) {
        int[] states = {1,1,0,0,1,1,0,1,1,1};
        int[] numbers = {3,4,15};
        lightBulbs(states, numbers);
        printStates(states);
    }
    public static void printStates(int[] states){
        for (int i = 0;i < states.length;i++){
            System.out.print(states[i]+" ");
        }
        System.out.println();
    }
    public static void lightBulbs(int[] states, int[] numbers){
        HashSet<Integer> primes = findPrimes(numbers);
        for(int i:primes){
            int num = i;
            while(num <= states.length){
                states[num-1] = states[num-1] == 1?0:1;
                num+=i;
            }
        }
    }

    public static HashSet<Integer> findPrimes(int[] numbers){
        HashSet<Integer> primes = new HashSet<Integer>();
        for(int i = 0;i < numbers.length;i++){
            HashSet<Integer> primeFactor = primeFactors(numbers[i]);
            for(int j:primeFactor){
                if(primes.contains(j)){
                    primes.remove(j);
                }
                else{
                    primes.add(j);
                }
            }
        }
        return primes;
    }

    public static HashSet<Integer> primeFactors(int n){
        HashSet<Integer> primeFactor = new HashSet<Integer>();
        if(n%2 == 0){
            primeFactor.add(2);
            while(n%2 == 0){
                n = n/2;
            }
        }
        for(int i = 3;i <= Math.sqrt(n);i++){
            if(n%i == 0){
                primeFactor.add(i);
                while(n%i == 0){
                    n = n/i;
                }
            }
        }
        if(n>2){
            primeFactor.add(n);
        }
        return primeFactor;
    }
}
