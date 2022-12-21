package com.gerenciador.gfapi.util;

import javax.swing.text.MaskFormatter;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatacao {

    private static DateFormat dateFormat;
    private static DateFormat dateFormatLenient;

    public static String getValorFormatado(String mascara, String valor) {
        for(int i = 0; i < valor.length(); ++i) {
            mascara = mascara.replaceFirst("[#]", valor.substring(i, i + 1));
        }

        return mascara.replaceAll("[#]", "");
    }

    public static String getDataHoraFormatada(Date valorData) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        }

        return dateFormat.format(valorData);
    }

    public static String getHoraFormatada(Date valorData) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
        }

        return dateFormat.format(valorData);
    }

    public static Date getHoraFormatada(String valorData) throws ParseException {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("HH:mm", new Locale("pt", "BR"));
        }

        return dateFormat.parse(valorData);
    }

    public static String getDataFormatada(Date valorData) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return df.format(valorData);
    }

    public static Date getDataFormatada(String valorData) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return df.parse(valorData);
    }

    public static String getDataExtenso(Date valor) {
        return (new SimpleDateFormat(" EEEEE, dd 'de' MMMMM 'de' yyyy", new Locale("pt", "BR"))).format(valor);
    }

    public static String lpad(String valueToPad, String filler, int size) {
        while(valueToPad.length() < size) {
            valueToPad = filler + valueToPad;
        }

        return valueToPad;
    }

    public static String rpad(String valueToPad, String filler, int size) {
        while(valueToPad.length() < size) {
            valueToPad = valueToPad + filler;
        }

        return valueToPad;
    }

    public static String limparCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");
        return cnpj;
    }

    public static String limparCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        return cpf;
    }

    public static String limparCep(String cep) {
        if (cep == null) {
            return cep;
        } else {
            cep = cep.replaceAll("-", "");
            cep = cep.replaceAll("\\.", "");
            return cep;
        }
    }

    public static String formatarCep(String cep) {
        return formatar(cep, "#####-###");
    }

    public static String limparTelefone(String fone) {
        if (fone == null) {
            return fone;
        } else {
            fone = fone.replace("-", "");
            fone = fone.replace("(", "");
            fone = fone.replace(")", "");
            fone = fone.replace(" ", "");
            return fone.trim();
        }
    }

    public static String limparCadastro(String cadastro) {
        if (!Utils.isNullOrEmpty(cadastro)) {
            cadastro = cadastro.replaceAll("[^a-zA-Z0-9\\s]", "");
        }

        return cadastro;
    }

    public static String formatar(String valor, String mascara) {
        if (valor == null) {
            return null;
        } else {
            String dado = "";

            int indMascara;
            for(indMascara = 0; indMascara < valor.length(); ++indMascara) {
                char c = valor.charAt(indMascara);
                if (Character.isDigit(c)) {
                    dado = dado + c;
                }
            }

            indMascara = mascara.length();
            int indCampo = dado.length();

            while(indCampo > 0 && indMascara > 0) {
                --indMascara;
                if (mascara.charAt(indMascara) == '#') {
                    --indCampo;
                }
            }

            String saida;
            for(saida = ""; indMascara < mascara.length(); ++indMascara) {
                saida = saida + (mascara.charAt(indMascara) == '#' ? dado.charAt(indCampo++) : mascara.charAt(indMascara));
            }

            return saida;
        }
    }

    public static String formatarCpf(String cpf) {
        while(cpf.length() < 11) {
            cpf = "0" + cpf;
        }

        return formatar(cpf, "###.###.###-##");
    }

    public static String formatarCnpj(String cnpj) {
        while(cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }

        return formatar(cnpj, "##.###.###/####-##");
    }

    public static String formatarCPFCNPJ(String valor) {
        if (!Utils.isNullOrEmpty(valor)) {
            String mask_pattern = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
            String mask_replace = "$1.$2.$3-$4";
            if (valor.length() > 11) {
                mask_pattern = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})";
                mask_replace = "$1.$2.$3/$4-$5";
            }

            Pattern pattern = Pattern.compile(mask_pattern);
            Matcher matcher = pattern.matcher(valor);
            if (matcher.matches()) {
                valor = matcher.replaceAll(mask_replace);
            }
        }

        return valor;
    }

    public static String remove_caracteres_cpf_cnpj(String valor) {
        return valor.replaceAll("\\D", "");
    }

    public static String remove_caracteres(String valor) {
        String toReturn = "";
        if (!Utils.isNullOrEmpty(valor)) {
            toReturn = valor.replaceAll("\\D", "");
        }

        return toReturn;
    }

    public static String remove_naoCaracteres(String valor) {
        String toReturn = "";
        if (!Utils.isNullOrEmpty(valor)) {
            toReturn = valor.replaceAll("[^a-zA-Z0-9\\s]", "");
        }

        return toReturn;
    }

    public static String formatarFoneFax(String valor) {
        try {
            if (valor != null && !"".equals(valor)) {
                valor = valor.trim();
                valor = valor.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", "");
            }

            MaskFormatter f = new MaskFormatter("(##) ####-####");
            f.setValueContainsLiteralCharacters(false);
            return f.valueToString(valor.replaceAll(" ", ""));
        } catch (ParseException var2) {
            System.out.println(var2.toString());
            return null;
        }
    }

    public static String formatarNumero(Object valor, String mascara) {
        DecimalFormat df = new DecimalFormat(mascara);
        return df.format(valor);
    }

    public static String formataValor(Double numero, int minFractionDigits, int maxFractionDigits) throws ParseException {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(maxFractionDigits);
        nf.setMinimumFractionDigits(minFractionDigits);
        return nf.format(numero);
    }

    public static String preencherComZeros(String numeroStr, int tamanhoCampo) {
        if (numeroStr.length() > tamanhoCampo) {
            throw new IllegalArgumentException("O numero contem mais posicoes que o esperado (" + tamanhoCampo + "):" + numeroStr.length());
        } else if (numeroStr.length() == tamanhoCampo) {
            return numeroStr;
        } else {
            StringBuffer numeroAjustado = new StringBuffer(tamanhoCampo);
            int repeat = tamanhoCampo - numeroStr.length();

            for(int i = 0; i < repeat; ++i) {
                numeroAjustado.append("0");
            }

            numeroAjustado.append(numeroStr);
            if (numeroAjustado.length() > tamanhoCampo) {
                throw new IllegalStateException("Tamanho de campo diferente do esperado.");
            } else {
                return numeroAjustado.toString();
            }
        }
    }
}
