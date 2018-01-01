package red.black.tree;

public class RedBlack {
    private No outer=new No(-1,false);
    private No root=outer;
    public void inset(int z){
        add(new No(z,true));
    }
    public void remove(int x){
        delete(buscar(x,root));
    }
    private void delete(No z){
        No y=z;
        System.out.println("z"+z.getKey());
        No x;
        boolean corY=y.isCor();
        if(z.getFilhoEsq()==outer){
            x = z.getsonRight();
            transplant(z, z.getsonRight());
        }else if(z.getsonRight()==outer ||z.getsonRight()==null){
            x = z.getFilhoEsq();
            transplant(z, z.getFilhoEsq());
        }else{
            y=sucessor(z.getsonRight());
            corY=y.isCor();
            x=y.getsonRight();
            if(y.getDad()==z){
                x.setDad(y);
            }else{
                transplant(y, y.getsonRight());
                y.setsonRight(z.getsonRight());
                y.getsonRight().setDad(y);
            }
            transplant(z, y);
            y.setFilhoEsq(z.getFilhoEsq());
            y.getFilhoEsq().setDad(y);
            y.setCor(z.isCor());
        }
        if(corY==false)
            deleteFixup(x);
    }
    private void deleteFixup(No x) {
        System.out.println("x: por isso "+x.getKey());
        System.out.println(x.isCor());
        while (x!=root && x.isCor()==false) {  
            System.out.println("x"+x.getKey());
            System.out.println("x.p.e"+x.getDad().getFilhoEsq().getKey());
            if(x==x.getDad().getFilhoEsq()){
                No w =x.getDad().getsonRight();
                if(w.isCor()){
                    w.setCor(false);
                    w.getDad().setCor(true);
                    leftRotate(x.getDad());
                    w=x.getDad().getsonRight();
                }
                if(w.getFilhoEsq().isCor()==false && w.getsonRight().isCor()==false){
                    w.setCor(true);
                    x=x.getDad();
                }else if(w.getsonRight().isCor()==false){
                    System.out.println("ihu");
                    w.getFilhoEsq().setCor(false);
                    w.setCor(true);
                    rightRotate(w);
                    w=x.getDad().getsonRight();
                    System.out.println("root");
                //}else{//esse else não tem no livro é a parte que ta só na pagina
                    w.setCor(x.getDad().isCor());
                    x.getDad().setCor(false);
                    w.getsonRight().setCor(false);
                    leftRotate(x.getDad());
                    x=root;
                }
            }else{//esse foi o q n foi feito no livro
                No w =x.getDad().getFilhoEsq();
                if(w.isCor()){
                    w.setCor(false);
                    w.getDad().setCor(true);
                    rightRotate(x.getDad());
                    w=x.getDad().getFilhoEsq();
                    
                }
                if(w.getsonRight().isCor()==false && w.getFilhoEsq().isCor()==false){
                    
                    w.setCor(true);
                    x=x.getDad();
                }else if(w.getFilhoEsq().isCor()==false){
                    System.out.println("qwert");
                    w.getsonRight().setCor(false);
                    w.setCor(true);
                    leftRotate(w);
                    w=x.getDad().getFilhoEsq();
                    System.out.println("root");
                //}else{//esse else não tem no livro é a parte que ta só na pagina
                    w.setCor(x.getDad().isCor());
                    x.getDad().setCor(false);
                    w.getFilhoEsq().setCor(false);
                    rightRotate(x.getDad());
                    x=root;
                }
            }
        }
        x.setCor(false);
    }
    private No sucessor(No x){
        while (x.getFilhoEsq() != outer) {            
            x=x.getFilhoEsq();
        }
        return x;
    }
    private void transplant(No u, No v){
        if(u.getDad()==outer){
            root=v;
        }else if(u==u.getDad().getFilhoEsq()){
            u.getDad().setFilhoEsq(v);
        }else{
            u.getDad().setsonRight(v);
        }
        v.setDad(u.getDad());
    }
    private No buscar(int x,No r){
        while (x!=r.getKey() && r.getKey()!=-1) {            
            if(r.getKey()>x){
                r=r.getFilhoEsq();
            }else if(r.getKey()<x){
                r=r.getsonRight();
            }
        }
        return r;
    }
    private void add(No z){
        No y=outer;
        No x=root;
        while(x!=outer){
            y=x;
            if(z.getKey()<x.getKey()){
                x=x.getFilhoEsq();
            }else{
                x=x.getsonRight();
            }
        }
        z.setDad(y);
        if(y==outer){
            root=z;
        }else if(z.getKey()<y.getKey()){//troquei o x por y
            y.setFilhoEsq(z);
            //System.out.println(z.getKey());
        }else
            y.setsonRight(z);
        //System.out.println("x"+x.getKey());
        //System.out.println("z"+z.getKey());
        z.setFilhoEsq(outer);
        z.setsonRight(outer);
        z.setCor(true);
        recolor(z);
    }
    private void recolor(No z) {
        while(z.getDad().isCor()){
            if(z.getDad()==z.getDad().getDad().getFilhoEsq()){//esse é que tem no livro
                No y=z.getDad().getDad().getsonRight();
                if(y.isCor()){
                    z.getDad().setCor(false);
                    y.setCor(false);
                    z.getDad().getDad().setCor(true);
                    z=z.getDad().getDad();
                }else{
                    if(z == z.getDad().getsonRight()){
                        z=z.getDad();
                        leftRotate(z);
                    }
                    z.getDad().setCor(false);
                    z.getDad().getDad().setCor(true);
                    rightRotate(z.getDad().getDad());
                }
            }else{
                No y=z.getDad().getDad().getFilhoEsq();
                if(y.isCor()){
                    z.getDad().setCor(false);
                    y.setCor(false);
                    z.getDad().getDad().setCor(true);
                    z=z.getDad().getDad();
                }else{
                    if(z == z.getDad().getFilhoEsq()){
                        z=z.getDad();
                        rightRotate(z);
                    }
                    z.getDad().setCor(false);
                    z.getDad().getDad().setCor(true);
                    leftRotate(z.getDad().getDad());
                }
            }
        }
        root.setCor(false);
    }
    private void leftRotate(No x) {
        No y=x.getsonRight();
        x.setsonRight(y.getFilhoEsq());
        if(y.getFilhoEsq() != outer){
            y.getFilhoEsq().setDad(x);
        }
        y.setDad(x.getDad());
        if(x.getDad()==outer){
            root=y;
        }else if(x==x.getDad().getFilhoEsq()){
            x.getDad().setFilhoEsq(y);
        }else x.getDad().setsonRight(y);
        y.setFilhoEsq(x);
        x.setDad(y);
    }
    private void rightRotate(No x) {
        No y=x.getFilhoEsq();
        x.setFilhoEsq(y.getsonRight());
        if(y.getsonRight()!= outer){
            y.getsonRight().setDad(x);
        }
        y.setDad(x.getDad());
        if(x.getDad()==outer){
            root=y;
        }else if(x==x.getDad().getsonRight()){
            x.getDad().setsonRight(y);
        }else x.getDad().setFilhoEsq(y);
        y.setsonRight(x);
        x.setDad(y);
    }
    public void display(){
        pre(root);
    }
    private void pre(No n){
        visit(n);
        if(n.getFilhoEsq()!=outer)
            pre(n.getFilhoEsq());
        if(n.getsonRight()!=outer)
            pre(n.getsonRight());
    }
    private void visit(No n){
        System.out.println("--------------------------------");
        System.out.println("Valor: "+(n.getKey()));
        System.out.println("Cor: "+(n.isCor() ? "Rubro" : "Negro"));
        System.out.println("Pai: "+((n.getDad().getKey() ==-1) ? "externo" : n.getDad().getKey()));
        System.out.println("Filho direito : "+((n.getsonRight().getKey() ==-1) ? "externo" : n.getsonRight().getKey()));
        System.out.println("Filho esquerdo: "+((n.getFilhoEsq().getKey() ==-1) ? "externo" : n.getFilhoEsq().getKey()));
      
    }
}
