public class Main {
    public static void main(String[] args) {
        Peça peça = new Peça("Peça A", 100, 50);
        System.out.println(peça.getInfo());

        PeçaImportada peçaImportada = new PeçaImportada("Peça B", 200, 80, 30, 20);
        System.out.println(peçaImportada.getInfo());
    }
}
