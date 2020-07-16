import java.math.BigDecimal;

public class Currency {

    private BigDecimal changeRate;
    private String nominal;
    private String code;
    private String name;
    private final String description;
    private String type;

    public Currency(String description) {
        this.description = description;
    }

    public String getChangeRate() {
        return changeRate.toString();
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = new BigDecimal(changeRate);
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal changeToManat(BigDecimal amount) {
        return amount.multiply(changeRate);
    }

    public BigDecimal changeToCurrency(BigDecimal amount) {
        return amount.divide(changeRate, 2, BigDecimal.ROUND_HALF_UP);
    }

    public String getDescription() {
        return "<html>QEYD: " + description + "</html>";
    }
}
