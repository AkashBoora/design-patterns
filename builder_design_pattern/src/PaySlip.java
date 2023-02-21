public class PaySlip {
    public  static  class  Builder{
        private String name;
        private String designation;
        private String location;
        private String bankName;
        private String bankAccountNo;
        private String panNo;
        private double basicPay;
        private double conveyance;
        private double hra;
        private double medicalAllowance;
        private double specialAllowance;

        public Builder() {
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder designation(String designation){
            this.designation = designation;
            return this;
        }
        public Builder location(String location){
            this.location = location;
            return this;
        }
        public Builder bankName(String bankName){
            this.bankName = bankName;
            return this;
        }
        public Builder bankAccountNo(String bankAccountNo){
            this.bankAccountNo = bankAccountNo;
            return this;
        }
        public Builder panNo(String panNo){
            this.panNo = panNo;
            return this;
        }
        public Builder basicPay(double basicPay){
            this.basicPay = basicPay;
            return this;
        }
        public Builder conveyance(double conveyance){
            this.conveyance = conveyance;
            return this;
        }
        public Builder hra(double hra){
            this.hra = hra;
            return this;
        }
        public Builder medicalAllowance(double medicalAllowance){
            this.medicalAllowance = medicalAllowance;
            return this;
        }
        public Builder specialAllowance(double specialAllowance){
            this.specialAllowance = specialAllowance;
            return this;
        }
        public PaySlip build(){
            return new PaySlip(this);
        }
    }
    private final String name;
    private final String designation;
    private final String location;
    private final String bankName;
    private final String bankAccountNo;
    private final String panNo;
    private final double basicPay;
    private final double conveyance;
    private final double hra;
    private final double medicalAllowance;
    private final double specialAllowance;
    private final double totalPayable;

    private PaySlip(Builder builder) {
        this.name = builder.name;
        this.designation = builder.designation;
        this.location = builder.location;
        this.bankName=builder.bankName;
        this.bankAccountNo=builder.bankAccountNo;
        this.panNo=builder.panNo;
        this.basicPay=builder.basicPay;
        this.conveyance=builder.conveyance;
        this.hra=builder.hra;
        this.medicalAllowance=builder.medicalAllowance;
        this.specialAllowance=builder.specialAllowance;
        this.totalPayable = builder.basicPay + builder.conveyance +builder.hra + builder.medicalAllowance + builder.specialAllowance;
    }

    @Override
    public String toString() {
        return "PaySlip{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", location='" + location + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNo='" + bankAccountNo + '\'' +
                ", panNo='" + panNo + '\'' +
                ", basicPay=" + basicPay +
                ", conveyance=" + conveyance +
                ", hra=" + hra +
                ", medicalAllowance=" + medicalAllowance +
                ", specialAllowance=" + specialAllowance +
                ", totalPayable=" + totalPayable +
                '}';
    }
}
