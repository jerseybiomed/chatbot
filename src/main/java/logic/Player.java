package logic;

public class Player {
    private Double rouletteBalance;
    private Double banditBalance;

    public void setBanditBalance(Double banditBalance) {
        this.banditBalance = banditBalance;
    }

    public Double getBanditBalance() {
        return banditBalance;
    }

    public void setRouletteBalance(Double rouletteBalance) {
        this.rouletteBalance = rouletteBalance;
    }

    public Double getRouletteBalance() {
        return rouletteBalance;
    }

}
