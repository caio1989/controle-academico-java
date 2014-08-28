package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import classes.backend.Disciplinas;
import dao.DisciplinasDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*classe responsavel por adcionar nova disciplina ao banco de dados
 * por favor, nao altere dessa linha para baixo, se voce nao tiver o minimo de conhecimento necessario
 * Sistema desenvolvido por Caio Cutrim, aluno*/
public class AdcionarDisciplinaGUI extends JPanel {
	private JTextField tfNome;


	public AdcionarDisciplinaGUI() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Digite o nome da disciplina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(71, 62, 377, 122);
		add(panel);
		panel.setLayout(null);
		
		tfNome = new JTextField();
		tfNome.setBounds(26, 50, 307, 26);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Disciplinas disciplina = new Disciplinas();
				disciplina.setNomeDisciplina(tfNome.getText());
				DisciplinasDAO dao = new DisciplinasDAO();
				dao.adcionarDisciplina(disciplina);
				JOptionPane.showMessageDialog(null, "Disciplina:" +disciplina.getNomeDisciplina()+" adcionada com sucesso!");
				
			}
		});
		btnSalvar.setBounds(660, 217, 117, 25);
		add(btnSalvar);

	}

}
