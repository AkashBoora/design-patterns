public class Director {
    public void createAssociateSoftwareEngineerPaySlip(PaySlip.Builder builder, String name, String accountNumber, String panNo){
        builder.name(name).designation("Associate Software Engineer").location("Hyderabad").bankName("Bank Name").bankAccountNo(accountNumber).panNo(panNo).basicPay(20000).conveyance(5000).hra(2000).medicalAllowance(1000).specialAllowance(1000);
    }

    public void createSoftwareEngineerIPaySlip(PaySlip.Builder builder, String name, String accountNumber, String panNo){
        builder.name(name).designation("Software Engineer I").location("Hyderabad").bankName("Bank Name").bankAccountNo(accountNumber).panNo(panNo).basicPay(40000).conveyance(7000).hra(3000).medicalAllowance(2000).specialAllowance(2000);
    }
}
