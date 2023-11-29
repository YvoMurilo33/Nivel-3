package cadastrobd;

import java.sql.SQLException;
import java.util.ArrayList;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

public class CadastroBDTeste {

    public static void main(String[] args) {
        PessoaFisicaDAO pfDao = new PessoaFisicaDAO();
        PessoaJuridicaDAO pjDao = new PessoaJuridicaDAO();

        try {
            
            PessoaFisica pf = new PessoaFisica("114455669988", null, "Mateus Araujo Azevedo",
                    "Rua Doutor Itamar Guará", "Vila Mariana", "MA", "65905215", "Mateus@gmail.com");

            
            if (pf.getNome() == null || pf.getNome().trim().isEmpty()) {
                System.out.println("O nome está nulo ou vazio antes da chamada de incluir.");
                return; 
            }

            
            int idPf = pfDao.incluir(pf);
            System.out.println("Pessoa Física incluída com o ID: " + idPf);

            
            pf.setCidade("Campo Grande");
            pf.setEstado("MS");
            pfDao.alterar(pf);

            
            ArrayList<PessoaFisica> listaPf = pfDao.getPessoas();
            listaPf.forEach(pessoa -> System.out.println(pessoa.getNome()));

            
            pfDao.excluir(pf);

            
            PessoaJuridica pj = new PessoaJuridica("74562368123", null, "Bianca Araujo Fernandes",
                    "Avenida Dom Pedro II", "PortoAlegre", "PO", "8845612398", "Bianca@outlook.com");

            
            if (pj.getNome() == null || pj.getNome().trim().isEmpty()) {
                System.out.println("O nome está nulo ou vazio antes da chamada de incluir.");
                return; 
            }

            
            int idPj = pjDao.incluir(pj);
            System.out.println("Pessoa Jurídica incluída com o ID: " + idPj);

            
            pj.setEndereco("Avenida Dom Pedro II");
            pjDao.alterar(pj);

            
            ArrayList<PessoaJuridica> listaPj = pjDao.getPessoas();
            listaPj.forEach(empresa -> System.out.println(empresa.getNome()));

            
            pjDao.excluir(pj);

        } catch (SQLException e) {
        } finally {
            try {
                pfDao.close();
                pjDao.close();
            } catch (SQLException e) {
            }
        }
    }
}
