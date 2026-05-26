

public class Main {
    public static void main(String[] args) throws Exception {

        ProdutoEstadual[] produtosE = new ProdutoEstadual[2];
        produtosE[0] = new ProdutoEstadual("Processador Intel", 860.00);
        produtosE[1] = new ProdutoEstadual("Processador AMD", 780.00);

        ProdutoNacional[] produtosN = new ProdutoNacional[2];
        produtosN[0] = new ProdutoNacional("Processador Intel", 860.00);
        produtosN[1] = new ProdutoNacional("Processador AMD", 780.00);

        ProdutoImportado[] produtosI = new ProdutoImportado[2];
        produtosI[0] = new ProdutoImportado("Processador Intel", 860.00);
        produtosI[1] = new ProdutoImportado("Processador AMD", 780.00);

        System.out.println("Produtos Estaduais");
        for (ProdutoEstadual p : produtosE) {
            System.out.println(p.getInfo());
            System.out.println();
        }

        System.out.println("Produtos Nacionais");
        for (ProdutoNacional p : produtosN) {
            System.out.println(p.getInfo());
            System.out.println();
        }

        System.out.println("Produtos Importados");
        for (ProdutoImportado p : produtosI) {
            System.out.println(p.getInfo());
            System.out.println();
        }
    }
}
