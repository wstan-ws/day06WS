public class test {
    
    public static void main(String[] args) {
        
        String line = "hello ";
        String one = line.trim().toLowerCase().split(" ")[0];
        String two = line.trim().toLowerCase().split(" ")[1];

        System.out.println(one);
        System.out.println(two);
    }
}
