import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Currency {

    private BigDecimal changeRate;
    private String nominal;
    private String code;
    private String name;
    private String description;

    public String getChangeRate() {
        return changeRate.toString();
    }

    public String getNominal() {
        return nominal;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    private String type;

    public Currency()
    {
        this.name = "Unknown";
        this.code = "NULL";
        this.nominal = "NULL";
        this.changeRate = new BigDecimal("1");
    }

    public Currency(String description)
    {
        this.description = description;
    }

    public Currency(String name, String code, BigDecimal changeRate)
    {
        this.name = name;
        this.code = code;
        this.changeRate = changeRate;
    }

    public BigDecimal changeToManat(BigDecimal amount)
    {
        return amount.multiply(changeRate);
    }

    public BigDecimal changeToCurrency(BigDecimal amount)
    {
        return amount.divide(changeRate, 2, BigDecimal.ROUND_HALF_UP);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChangeRate(String changeRate) {
        this.changeRate = new BigDecimal(changeRate);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return "<html>QEYD: " + description + "</html>";
    }
}
