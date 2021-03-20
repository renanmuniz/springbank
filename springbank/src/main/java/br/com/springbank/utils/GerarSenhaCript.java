package br.com.springbank.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.*;

public class GerarSenhaCript {
        public static void main(String[] args) {
        String senha = JOptionPane.showInputDialog("Digite a senha que deseja criptografar");
        String senhaCripto = new BCryptPasswordEncoder().encode(senha);
        System.out.println(senhaCripto);
    }
}
