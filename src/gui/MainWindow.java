package gui;

import model.Company;
import model.Name;
import repository.CompanyRepository;
import repository.NameRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class MainWindow {
    private JButton button1;
    private JPanel panel1;

    public MainWindow() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Company> companies = CompanyRepository.getInstance().findCompanies();
                for (Company c : companies)
                    System.out.println(c);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
