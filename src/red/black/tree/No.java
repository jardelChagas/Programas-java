package red.black.tree;

public final class No {
    public No(){}
    public No(int x,boolean cor){
        setKey(x);
        setCor(cor);
    }
    private int key;
    private No dad;
    private No sonRight;
    private No filhoEsq;
    private boolean cor;

    public int getKey() {
        return key;
    }
    public void setKey(int value) {
        this.key = value;
    }
    public No getDad() {
        return dad;
    }
    public void setDad(No dad) {
        this.dad = dad;
    }
    public No getsonRight() {
        return sonRight;
    }
    public void setsonRight(No filhoDir) {
        this.sonRight = filhoDir;
    }
    public No getFilhoEsq() {
        return filhoEsq;
    }
    public void setFilhoEsq(No filhoEsq) {
        this.filhoEsq = filhoEsq;
    }
    public boolean isCor() {
        return cor;
    }
    public void setCor(boolean cor) {
        this.cor = cor;
    }
}
