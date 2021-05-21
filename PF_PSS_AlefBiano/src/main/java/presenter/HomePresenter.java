/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenter;

import collection.UtilitarioString;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.Home;

/**
 *
 * @author biano
 */
public class HomePresenter {

    private Home display;
    private static HomePresenter hP;

    public static HomePresenter gethP() {
        if (hP == null) {
            hP = new HomePresenter();
        }
        return hP;
    }

    private HomePresenter() {
        display = new Home();
        display.setVisible(true);
        listener(display);
    }

    private void listener(Home display) {
        display.getTxtOriginal().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent arg0) {
                display.getTxtProcessado().setText("");
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                display.getTxtProcessado().setText("");
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                display.getTxtProcessado().setText("");
            }
        });

        display.getBtnProcessar().addActionListener((ActionEvent arg0) -> {
            if (display.getTxtOriginal().getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Não foi inserido nenhum texto para ser processado", "Nenhum texto foi inserido", JOptionPane.ERROR_MESSAGE);
            } else {
                var texto = display.getTxtOriginal().getText();
                var palavras = getPalavras();
                var simbolo = "*";
                var textoModificado = UtilitarioString.getInstanciaUtilitarioString().substitui(texto, palavras, simbolo);
                display.getTxtProcessado().setText(textoModificado);
            }

        });

        display.getBtnAdicionar().addActionListener((ActionEvent arg0) -> {
            try {
                adicionarMarca();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Marca já cadatrada", JOptionPane.ERROR_MESSAGE);
            }
        });

        display.getBtnDeletar().addActionListener((ActionEvent arg0) -> {
            try {
                deletarMarca();
            } catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null, "Não foi selecionado nenhum item a ser excluído!", "Nenhum item selecionado", JOptionPane.ERROR_MESSAGE);
            }
        }
        );

    }

    private void adicionarMarca() throws Exception {
        var novaMarca = JOptionPane.showInputDialog("Insira uma marca");
        if (novaMarca == null) {

        } else {

            if (novaMarca.isBlank()) {
                JOptionPane.showMessageDialog(null, "Não foi inserido um nome para a marca", "Nenhuma marca inserida", JOptionPane.ERROR_MESSAGE);
            } else {
                display.getTxtProcessado().setText("");
                DefaultListModel modelo = new DefaultListModel();
                var palavras = getPalavras();
                for (var palavra : palavras) {
                    if (novaMarca.equals(palavra)) {
//                        JOptionPane.showMessageDialog(null, "Essa marca já foi cadastrada", "Marca já cadatrada", JOptionPane.ERROR_MESSAGE);
                        throw new Exception("Essa marca já foi cadastrada");
                    }
                    modelo.addElement(palavra);
                }
                modelo.addElement(novaMarca);
                display.getLstMarcas().setModel(modelo);
            }
        }

    }

    private void deletarMarca() {
        display.getTxtProcessado().setText("");
        if (display.getLstMarcas().getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(null, "Não há nenhum item na lista");
        } else {
            DefaultListModel modelo = new DefaultListModel();
            var palavras = getPalavras();
            for (var palavra : palavras) {
                modelo.addElement(palavra);
            }
            var teste = (display.getLstMarcas().getSelectedIndex());
            modelo.removeElementAt(teste);
            display.getLstMarcas().setModel(modelo);
        }

    }

    private ArrayList<String> getPalavras() {
        ArrayList<String> palavras = new ArrayList();
        for (int i = 0; i < display.getLstMarcas().getModel().getSize(); i++) {
            var palavra = display.getLstMarcas().getModel().getElementAt(i);
            palavras.add(palavra);
        }
        return palavras;
    }
}
