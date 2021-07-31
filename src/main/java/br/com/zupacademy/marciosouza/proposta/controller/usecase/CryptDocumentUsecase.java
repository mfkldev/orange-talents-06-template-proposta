package br.com.zupacademy.marciosouza.proposta.controller.usecase;

import org.springframework.security.crypto.encrypt.Encryptors;

public class CryptDocumentUsecase {

    private static CharSequence salt = "5f5f47287021365a757b2667743b452b445a75433172454b6c734e5e2b7e332a";

    public static String encryptDocument(String document){
        return Encryptors.queryableText("document", salt).encrypt(document);
    }

    public static String dencryptDocument(String document){
        return Encryptors.queryableText("document", salt).decrypt(document);
    }
}
