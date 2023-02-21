public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        PaySlip.Builder builder = new PaySlip.Builder();
        director.createAssociateSoftwareEngineerPaySlip(builder, "Akash", "7894456213", "ERTF4563F");
        PaySlip paySlip1 = builder.build();
        System.out.println(paySlip1.toString());
        director.createSoftwareEngineerIPaySlip(builder, "Nivesh", "123456789", "YIUYU67845D");
        PaySlip paySlip2 = builder.build();
        System.out.println(paySlip2.toString());
    }
}