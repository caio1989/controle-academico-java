package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classes.backend.Aluno;
import classes.backend.Disciplinas;
import dao.AlunoDAO;
import dao.DisciplinasDAO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class NotasAlunosGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField tfNota6 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota5 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota4 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota3 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota2 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota1 = new JFormattedTextField(Mascara("##.##"));
	private JFormattedTextField tfNota7 = new JFormattedTextField(Mascara("##.##"));
	private JComboBox<String> cbAluno = new JComboBox<String>();
	private JFormattedTextField tfMedia = new JFormattedTextField();
	private JComboBox<String> cbDisciplina = new JComboBox<String>();
	private String resultado;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public NotasAlunosGUI() throws SQLException {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Selecione o nome da disciplina", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(43, 34, 350, 77);
		add(panel);
		panel.setLayout(null);
	
		cbDisciplina.setEditable(true);
		cbDisciplina.setBounds(22, 25, 308, 26);
		DefaultComboBoxModel<String> modeloDisciplina = new DefaultComboBoxModel<String>();
		ArrayList<Disciplinas> listaDisciplina = new ArrayList<Disciplinas>();
		modeloDisciplina.addElement("");
		listaDisciplina = new DisciplinasDAO().listarNomeDisciplinas();
		for (Disciplinas disciplina : listaDisciplina) {
			modeloDisciplina.addElement(disciplina.getNomeDisciplina().toString());
		}
		AutoCompleteDecorator.decorate(cbDisciplina);
		cbDisciplina.setModel(modeloDisciplina);
		panel.add(cbDisciplina);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Selecione o nome do aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(405, 34, 486, 77);
		add(panel_1);
		
		
		cbAluno.setEditable(true);
		cbAluno.setBounds(27, 27, 432, 26);
		DefaultComboBoxModel<String> modeloAluno = new DefaultComboBoxModel<String>();
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		modeloAluno.addElement("");
		listaAluno = new AlunoDAO().listaNomeAluno();
		for (Aluno aluno : listaAluno) {
			modeloAluno.addElement(aluno.getNomeAluno().toString());
		}
		
		AutoCompleteDecorator.decorate(cbAluno);
		cbAluno.setModel(modeloAluno);
		panel_1.add(cbAluno);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Digite o valor das notas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(43, 148, 689, 109);
		add(panel_2);
		panel_2.setLayout(null);
		
		
		tfNota6.setBounds(508, 38, 73, 30);
		panel_2.add(tfNota6);
		
	
		tfNota5.setBounds(408, 38, 73, 30);
		panel_2.add(tfNota5);
		
		
		tfNota4.setBounds(311, 38, 73, 30);
		panel_2.add(tfNota4);
		
		
		tfNota3.setBounds(212, 38, 73, 30);
		panel_2.add(tfNota3);
		
		
		tfNota2.setBounds(115, 38, 73, 30);
		panel_2.add(tfNota2);
		
		
		tfNota1.setBounds(18, 38, 73, 30);
		panel_2.add(tfNota1);
		
		
		tfNota7.setBounds(604, 38, 73, 30);
		panel_2.add(tfNota7);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Media", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(43, 269, 245, 85);
		add(panel_3);
		panel_3.setLayout(null);
		tfMedia.setEditable(false);
		
		
		tfMedia.setBounds(49, 28, 149, 30);
		panel_3.add(tfMedia);
		bloquearCampos();
		JButton btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Clique aqui para Desbloquear os campos");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloquearCampos();
				limparCampos();
			}
		});
		btnNovo.setForeground(Color.RED);
		btnNovo.setBounds(615, 329, 117, 25);
		add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setToolTipText("Clique aqui para salvar os dados");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluno aluno = new Aluno();
				Disciplinas disciplina = new Disciplinas();
				String nomeDisciplina = cbDisciplina.getSelectedItem().toString();
				disciplina.setNomeDisciplina(nomeDisciplina);
				float media =0;
				float count =0;
				getDadosTextField(aluno);
				ArrayList <Float> conta =aluno.getNotas();
				for(Float contagem : conta){
					contagem+=count;
					count = contagem;//vai receber o valor total da contagem que é a soma entre os valores do vetor
				}
				media = count/conta.size();
				DecimalFormat ftmt = new DecimalFormat("00.00");
				tfMedia.setText(ftmt.format(media));
				if(media<7){
					resultado = "Reprovado";
					aluno.setResultado(resultado);
				}else{
					resultado = "Aprovado";
					aluno.setResultado(resultado);
				}
				aluno.setMedia(media);
				aluno.setNomeAluno(cbAluno.getSelectedItem().toString());
				AlunoDAO dao = new AlunoDAO();
				try {
					dao.DisciplinaAluno(aluno, disciplina);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				JOptionPane.showMessageDialog(null, "Notas cadastradas para a disciplina '"+disciplina.getNomeDisciplina()+"'");
				limparCampos();
				bloquearCampos();
			}	
		});
		btnSalvar.setBounds(754, 329, 117, 25);
		add(btnSalvar);
	}
	//alguns metodos para a prórpia classe
	public JTextField[] receberCampos(){
		JTextField[]campos = {tfNota1,tfNota2,tfNota3,tfNota4,tfNota5,tfNota6,tfNota7};
		return campos;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox[]receberCombos(){
		JComboBox[]campos = {cbAluno,cbDisciplina};
		return campos;
	}
	public void getDadosTextField(Aluno aluno){
		ArrayList<Float> lista = new ArrayList<Float>();
		JTextField[]campos = receberCampos();
		for (int i=0;i<campos.length;i++){
			lista.add(Float.parseFloat(campos[i].getText().toString()));
			aluno.setNotas(lista);
		}
	}
	public void limparCampos(){
		JTextField[]campos = receberCampos();
		for (int i=0;i<campos.length;i++){
		campos[i].setText("");
		}
		JComboBox[]combos = receberCombos();
		for (int i=0;i<combos.length;i++){
		combos[i].setSelectedItem("");
		}
		
	}
	public void bloquearCampos(){
		JTextField[]campos = receberCampos();
		for (int i=0;i<campos.length;i++){
		campos[i].setEnabled(false);
		}
		JComboBox[]combos = receberCombos();
		for (int i=0;i<combos.length;i++){
		combos[i].setEnabled(false);
		}
		
	}
	public void desbloquearCampos(){
		JTextField[]campos = receberCampos();
		for (int i=0;i<campos.length;i++){
		campos[i].setEnabled(true);
		}
		JComboBox[]combos = receberCombos();
		for (int i=0;i<combos.length;i++){
		combos[i].setEnabled(true);
		}
		
	}
	//adcionando as mascaras
	public MaskFormatter Mascara(String Mascara) {
		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(Mascara); // Atribui a mascara
			F_Mascara.setPlaceholderCharacter(' '); // Caracter para
													// preencimento
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}
	}