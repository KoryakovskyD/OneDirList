
public class Main {
    public static void main(String[] args) {
        OneDirList<String> lst = new OneDirList<>();
        lst.addToHead("111");
        lst.addToTail("222");
        lst.addToTail("333");
        for (String s : lst) {
            System.out.println(s);
        }
        System.out.println("***");
        
        for (String s : lst.after("222")) {
            System.out.println(s);
        }
        System.out.println("***");


        for (String s : lst.after("zzz")) {
            System.out.println();
        }
        System.out.println("***");
        
        for (String s : lst.before("333")) {
            System.out.println(s);
        }
        System.out.println("***");


        for (String s : lst.before("zzz")) {
            System.out.println(s);
        }
        /*lst.printAll();
        System.out.println();
        
        lst.addToHead("AAA");
        lst.printAll();
        System.out.println();
        
        lst.remove("222");
        lst.remove("444");
        lst.printAll();
        System.out.println();
        
        lst.removeFromHead();
        lst.removeFromTail();
        lst.printAll();
        System.out.println();
        
        lst.addToHead("222");
        String s = (String) lst.removeFromHead();
        System.out.println(s);*/
    }
}
