import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author infernalsage
 */

public class Main extends javax.swing.JFrame {
    List<Currency> currencies;
    XmlParser parser;
    private javax.swing.JLabel manatLabel;
    private javax.swing.JLabel currencyLabel;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable currencyTable;
    private javax.swing.JTextField manatField;
    private javax.swing.JTextField currencyField;
    public Main() {
        initComponents();
    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }

    private void initComponents() {

        parser = new XmlParser();
        currencies = parser.readCurrencies("https://www.cbar.az/currencies/17.07.2020.xml");

        String[] columns = new String[]{"Valyuta", "Kod", "Kurs"};
        String[][] data = new String[currencies.size()][];

        for (int i = 0; i < currencies.size(); i++) {
            Currency row = currencies.get(i);
            data[i] = new String[]{row.getName(), row.getCode(), row.getChangeRate()};
        }

        jScrollPane1 = new javax.swing.JScrollPane();
        currencyTable = new javax.swing.JTable();
        manatField = new javax.swing.JTextField();
        manatLabel = new javax.swing.JLabel();
        currencyLabel = new javax.swing.JLabel();
        currencyField = new javax.swing.JTextField();
        noteLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currencyTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                columns
        ) {
            final Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            final boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        currencyTable.getTableHeader().setReorderingAllowed(false);
        currencyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        currencyTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                currencyTableMouseClicked();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        manatField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                manatFieldTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                manatFieldTextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                manatFieldTextChanged();
            }
        });

        currencyField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                currencyFieldTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                currencyFieldTextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                currencyFieldTextChanged();
            }
        });
        jScrollPane1.setViewportView(currencyTable);

        manatLabel.setText("Manat");

        currencyLabel.setText("Valyuta");

        noteLabel.setText(currencies.get(0).getDescription());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(manatLabel)
                                                        .addComponent(currencyLabel))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(currencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(manatField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(manatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(manatLabel))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(currencyLabel)
                                        .addComponent(currencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setResizable(false);

        pack();
    }

    private void manatFieldTextChanged() {
        try {
            int row = currencyTable.getSelectedRow();
            Currency currency = currencies.get(row);
            currencyField.setText(currency.changeToCurrency(new BigDecimal(manatField.getText())).toString());
        } catch (Exception ignored) {
        }
    }

    private void currencyFieldTextChanged() {
        try {
            int row = currencyTable.getSelectedRow();
            Currency currency = currencies.get(row);
            manatField.setText(currency.changeToManat(new BigDecimal(currencyField.getText())).toString());
        } catch (Exception ignored) {
        }
    }

    private void currencyTableMouseClicked() {
        try {
            int row = currencyTable.getSelectedRow();
            Currency currency = currencies.get(row);
            manatField.setText("1");
        } catch (Exception ignored) {

        }
    }
}
