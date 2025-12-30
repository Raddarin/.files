package textprocview;

//import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Container;
//import java.awt.Desktop.Action;
//import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import textproc.GeneralWordCounter;

public class BookReaderController {
    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));


    }
    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

      
        




        //=================Lista alla orden===============================

        List<Map.Entry<String,Integer>> ord_antal_par = counter.getWordList();

        SortedListModel<Map.Entry<String, Integer>> listmodell = new SortedListModel<>(ord_antal_par);

        JList<Map.Entry<String, Integer>> list = new JList<>(listmodell);

        JScrollPane scrollPane = new JScrollPane(list);

        pane.add(scrollPane);


        //=======================Knappar====================================
        JPanel interactionBar = new JPanel();

        JButton Aphabetic = new JButton("Aphabetic");
        JButton Frequency  = new JButton("Frequency");
        interactionBar.add(Aphabetic);
        interactionBar.add(Frequency);

        pane.add(interactionBar, BorderLayout.SOUTH);


        Aphabetic.addActionListener(e ->{
            listmodell.sort(Comparator.comparing(Map.Entry::getKey));
        });
        Frequency.addActionListener(e ->{
            listmodell.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        });

        JTextField textField = new JTextField(20);

        interactionBar.add(textField);

        JButton search = new JButton("Search");
        interactionBar.add(search);

        search.addActionListener(e -> {
            String searchWord = textField.getText();
            int index = -1;
            for(int i = 0; i<listmodell.getSize(); i++){
                //String key = listmodell.getElementAt(i).getKey();
                //System.out.println("Jämför" + searchWord + "med: '" + key + "'");
                if(searchWord.trim().equalsIgnoreCase(listmodell.getElementAt(i).getKey())){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                System.out.println("No result");
                JOptionPane.showMessageDialog(null, "No result");
            }
            else{
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
                System.out.println(index);
                
            }
        });

        //==================Keybind==================
        Action sortAction = new AbstractAction() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchWord = textField.getText();
                int index = -1;
                for(int i = 0; i<listmodell.getSize(); i++){
                    //String key = listmodell.getElementAt(i).getKey();
                    //System.out.println("Jämför" + searchWord + "med: '" + key + "'");
                    if(searchWord.trim().equalsIgnoreCase(listmodell.getElementAt(i).getKey())){
                        index = i;
                        break;
                    }
                }
                if(index == -1){
                    System.out.println("No result");
                    JOptionPane.showMessageDialog(null, "No result");
                }
                else{
                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                    System.out.println(index);
                    
                }
            }
        };

        search.addActionListener(sortAction);
        InputMap im = search.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = search.getActionMap();
        im.put(KeyStroke.getKeyStroke("ENTER"), "sortera");
        am.put("sortera", sortAction);

        frame.pack();
        frame.setVisible(true);
    }
       
}
