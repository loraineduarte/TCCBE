import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Main{

    public static void main(String argv[]) {

        try {

            File fXmlFile = new File("/home/loraine/IdeaProjects/TCC/XML/ID1/AlanMitchellDurham.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();



            //Passo 1: obter a tag principal e o ID - FEITO
            String Lattes = doc.getDocumentElement().getAttribute("NUMERO-IDENTIFICADOR");
            System.out.println(Lattes);

            // Dados Gerais
            Element elem = doc.getDocumentElement();
            String Nome= "";
            NodeList d =elem.getElementsByTagName("DADOS-GERAIS");
            Element tagDados = (Element) d.item( 0 );
            Nome = tagDados.getAttribute("NOME-COMPLETO");
            System.out.println(Nome);

            //Endereço
            String Universidade = "";
            String CodigoUniversidade = "";
            NodeList Endereco = elem.getElementsByTagName("ENDERECO-PROFISSIONAL");
            Element tagEndereco = (Element) Endereco.item(0);
            Universidade = tagEndereco.getAttribute("NOME-INSTITUICAO-EMPRESA");
            CodigoUniversidade = tagEndereco.getAttribute("CODIGO-ORGAO");
            System.out.println(Universidade);
            System.out.println(CodigoUniversidade);


            ArrayList Producoes = null; //Todas as produções


            System.out.println("----------Trabalhos Publicados ------------------");
            //Trabalhos em Eventos
            NodeList TrabEventos = elem.getElementsByTagName("TRABALHO-EM-EVENTOS");
            System.out.println("Trabalhos em Eventos : " + TrabEventos.getLength());


            System.out.println(TrabEventos.getLength());
            for(int j=0; j<TrabEventos.getLength(); j++){
                Element trabalho = (Element) TrabEventos.item(j);
                NodeList DadosBasicos = trabalho.getElementsByTagName("DADOS-BASICOS-DO-TRABALHO");
                Element NomeTrabalho = (Element) DadosBasicos.item(0);
                String NomeProd = NomeTrabalho.getAttribute("TITULO-DO-TRABALHO");
                System.out.println(NomeProd);
                String AnoProd = NomeTrabalho.getAttribute("ANO-DO-TRABALHO");
                System.out.println(AnoProd);
                String LocalProducao ="";
                String LocalProd = NomeTrabalho.getAttribute("PAIS-DO-EVENTO");
                String Idioma = NomeTrabalho.getAttribute("IDIOMA");
                LocalProducao= DefinirNacionalidadeProducao(LocalProd, Idioma);

                NodeList Detalhes = trabalho.getElementsByTagName("DETALHAMENTO-DO-TRABALHO");
                Element DetalhesTrabalho = (Element) Detalhes.item(0);
                String LocalPublicacao = DetalhesTrabalho.getAttribute("TITULO-DOS-ANAIS-OU-PROCEEDINGS");
                System.out.println(LocalPublicacao);

                //chamar a funçao para acrescentar no banco

            }
            System.out.println("-----------------------------------------------------------------");


            //Artigos Publicados
            NodeList ArtigosPublicados = elem.getElementsByTagName("ARTIGO-PUBLICADO");
            System.out.println("----------Artigos Publicados ------------------");
            System.out.println("Artigos Publicados: " + ArtigosPublicados.getLength());

            for(int j=0; j<ArtigosPublicados.getLength(); j++){
                Element trabalho = (Element) ArtigosPublicados.item(j);
                NodeList DadosBasicos = trabalho.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");
                Element NomeArtigo = (Element) DadosBasicos.item(0);
                String NomeProd = NomeArtigo.getAttribute("TITULO-DO-ARTIGO");
                System.out.println(NomeProd);
                String AnoProd = NomeArtigo.getAttribute("ANO-DO-ARTIGO");
                System.out.println(AnoProd);
                String LocalProducao = "";
                String LocalProd = NomeArtigo.getAttribute("PAIS-DE-PUBLICACAO");
                String Idioma = NomeArtigo.getAttribute("IDIOMA");
                LocalProducao= DefinirNacionalidadeProducao(LocalProd, Idioma);

                NodeList Detalhes = trabalho.getElementsByTagName("DETALHAMENTO-DO-ARTIGO");
                Element DetalhesTrabalho = (Element) Detalhes.item(0);
                String LocalPublicacao = DetalhesTrabalho.getAttribute("TITULO-DO-PERIODICO-OU-REVISTA");
                System.out.println(LocalPublicacao);

                //chamar a funçao para acrescentar no banco

            }

            System.out.println("-----------------------------------------------------------------");

            //Livros
            NodeList Livros = elem.getElementsByTagName("LIVRO-PUBLICADO-OU-ORGANIZADO");
            System.out.println("----------Livros Publicados ------------------");
            System.out.println("Livros Publicados:  " + Livros.getLength());

            for(int j=0; j<Livros.getLength(); j++){
                Element trabalho = (Element) Livros.item(j);
                NodeList DadosBasicos = trabalho.getElementsByTagName("DADOS-BASICOS-DO-LIVRO");
                Element NomeArtigo = (Element) DadosBasicos.item(0);
                String NomeProd = NomeArtigo.getAttribute("TITULO-DO-LIVRO");
                System.out.println(NomeProd);
                String AnoProd = NomeArtigo.getAttribute("ANO");
                System.out.println(AnoProd);
                String LocalProducao = "";
                String LocalProd = NomeArtigo.getAttribute("PAIS-DE-PUBLICACAO");
                String Idioma = NomeArtigo.getAttribute("IDIOMA");
                LocalProducao= DefinirNacionalidadeProducao(LocalProd, Idioma);
                NodeList Detalhes = trabalho.getElementsByTagName("DETALHAMENTO-DO-LIVRO");
                Element DetalhesTrabalho = (Element) Detalhes.item(0);
                String LocalPublicacao = DetalhesTrabalho.getAttribute("NOME-DA-EDITORA");
                System.out.println(LocalPublicacao);

                //chamar a funçao para acrescentar no banco

            }

            System.out.println("-----------------------------------------------------------------");

            //Capitulos
            NodeList Capitulos = elem.getElementsByTagName("CAPITULO-DE-LIVRO-PUBLICADO");
            System.out.println("Capitulos Publicados:  " + Capitulos.getLength());

            //outras produçoes bibliograficas
            NodeList Outros = elem.getElementsByTagName("OUTRA-PRODUCAO-BIBLIOGRAFICA");
            System.out.println("Outros Publicados:  " + Outros.getLength());

            //traducao
            NodeList Traducoes = elem.getElementsByTagName("TRADUCAO");
            System.out.println("Traduçoes:  " + Traducoes.getLength());

            //Apresentaçoes de Trabalho
            NodeList Apresentacoes = elem.getElementsByTagName("APRESENTACAO-DE-TRABALHO");
            System.out.println("Apresentaçoes de Trabalho:  " + Apresentacoes.getLength());

           //MiniCursos
            NodeList MiniCursos = elem.getElementsByTagName("CURSO-DE-CURTA-DURACAO-MINISTRADO");
            System.out.println("Mini-Cursos :  " + MiniCursos.getLength());

            //Organizaçao de Eventos
            NodeList OrgEventos = elem.getElementsByTagName("ORGANIZACAO-DE-EVENTO");
            System.out.println("Organizaçao de Eventos :  " + OrgEventos.getLength());

            //Outras Produçoes Tecnicas
            NodeList OutrasProducoesTecnicas = elem.getElementsByTagName("OUTRA-PRODUCAO-TECNICA");
            System.out.println("Outras Produçoes Tecnicas :  " + OutrasProducoesTecnicas.getLength());

            //Orientaçoes Concluidas para Mestrado
            NodeList OrintConclMestrado = elem.getElementsByTagName("ORIENTACOES-CONCLUIDAS-PARA-MESTRADO");
            System.out.println("Orientaçoes Concluidas para Mestrado :  " + OrintConclMestrado.getLength());

            //Orientaçoes Concluidas para Doutorado
            NodeList OrintConclDoutorado = elem.getElementsByTagName("ORIENTACOES-CONCLUIDAS-PARA-DOUTORADO");
            System.out.println("Orientaçoes Concluidas para Doutorado :  " + OrintConclDoutorado.getLength());

            //Outras Orientaçoes Concluidas
            NodeList OutrasOrintConcl = elem.getElementsByTagName("OUTRAS-ORIENTACOES-CONCLUIDAS");
            System.out.println("Outras Orientaçoes Concluidas :  " + OutrasOrintConcl.getLength());

            //Demais Trabalhos
            NodeList DemaisTrabalhos = elem.getElementsByTagName("DEMAIS-TRABALHOS");
            System.out.println("Demais Trabalhos:  " + DemaisTrabalhos.getLength());

            //PARTICIPACAO-EM-BANCA-DE-MESTRADO
            //PARTICIPACAO-EM-BANCA-DE-DOUTORADO
            //PARTICIPACAO-EM-BANCA-DE-EXAME-QUALIFICACAO
            //PARTICIPACAO-EM-BANCA-DE-APERFEICOAMENTO-ESPECIALIZACAO
            //PARTICIPACAO-EM-BANCA-DE-GRADUACAO

            //Participacao em Congresso
            NodeList PartiCongresso = elem.getElementsByTagName("PARTICIPACAO-EM-CONGRESSO");
            System.out.println("Participacao em Congresso:  " + PartiCongresso.getLength());

            //Orientaçoes em andamento - mestrado
            NodeList OrienAndamentoMestrado = elem.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-MESTRADO");
            System.out.println("Orientaçoes em andamento - mestrado:  " + OrienAndamentoMestrado.getLength());

            //Orientaçoes em andamento - doutorado
            NodeList OrienAndamentoDoutorado = elem.getElementsByTagName("ORIENTACAO-EM-ANDAMENTO-DE-DOUTORADO");
            System.out.println("Orientaçoes em andamento - doutorado:  " + OrienAndamentoDoutorado.getLength());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //funçao de olhar local publicaçao
    static String DefinirNacionalidadeProducao(String LocalPublicaçao, String Idioma){
        String retorno ="";
        if (LocalPublicaçao.equals(""))
        {
            if(Idioma.equals("Português")){
                retorno="1";
            }
            else{
                retorno="0";
            }
        }
        else if(LocalPublicaçao.equals("Brasil")){
            retorno = "1";
        }
        else{
            retorno ="0";
        }
        System.out.println(retorno);
        return retorno;
    }

}
