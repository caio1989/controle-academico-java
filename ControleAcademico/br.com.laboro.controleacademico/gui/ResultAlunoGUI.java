package gui;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import classes.backend.Aluno;
import classes.backend.Disciplinas;
import classes.backend.Endereco;
import classes.backend.Turma;

import componentes.ModeloTabela;
import dao.AlunoDAO;
import factory.ConnectionFactory;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/**/
public class ResultAlunoGUI extends JInternalFrame {
	private JTable tbDisciplinas;
	Connection connection;
	public void iniciarJanela() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultAlunoGUI frame = new ResultAlunoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ResultAlunoGUI() throws ClassNotFoundException {
		connection =  ConnectionFactory.getConnection();
		
		setClosable(true);
		setIconifiable(true);
		setTitle("Resultado de alunos");
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 5),
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		setBounds(100, 100, 894, 506);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Digite o nome do aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 26, 522, 79);
		getContentPane().add(panel);
		panel.setLayout(null);

		final JComboBox cbAluno = new JComboBox();
		DefaultComboBoxModel modeloAluno = new DefaultComboBoxModel();
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		modeloAluno.addElement("");
		try {
			listaAluno = new AlunoDAO().listaNomeAluno();
			for (Aluno aluno : listaAluno) {
				modeloAluno.addElement(aluno.getNomeAluno().toString());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AutoCompleteDecorator.decorate(cbAluno);
		cbAluno.setModel(modeloAluno);
		panel.add(cbAluno);
		
		
		cbAluno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String sql = "Select turma.nomeTurma,disciplinas.nome, disciplinasDoAluno.nota_1,disciplinasDoAluno.nota_2, disciplinasDoAluno.nota_3,disciplinasDoAluno.nota_4," +
						"disciplinasDoAluno.nota_5,disciplinasDoAluno.nota_6,disciplinasDoAluno.nota_7, disciplinasDoAluno.media, disciplinasDoAluno.resultado" +
						" from aluno " +
						"inner join disciplinasDoAluno on aluno.idAluno = disciplinasDoAluno.aluno_idAluno " +
						"inner join disciplinas on disciplinas.idDisciplinas = disciplinasDoAluno.disciplinas_idDisciplinas " +
						"inner join turma on disciplinasDoAluno.aluno_turma_idTurma = turma.idTurma and aluno.nomeAluno = '"+cbAluno.getSelectedItem().toString()+"'";
				try {
					preencherTabelaNotas(sql);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbAluno.setEditable(true);
		cbAluno.setBounds(25, 30, 434, 25);
		AutoCompleteDecorator.decorate(cbAluno);
		panel.add(cbAluno);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Notas das disciplinas do aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 174, 842, 253);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 35, 791, 185);
		panel_1.add(scrollPane);

		tbDisciplinas = new JTable();
		scrollPane.setViewportView(tbDisciplinas);

	}

	public void preencherTabelaNotas(String sql) throws ClassNotFoundException,
			SQLException {
		ArrayList<Float>notas = new ArrayList<Float>();
		ArrayList<Object[]> dados = new ArrayList<Object[]>();
		String[] colunas = new String[] {" Turma", "Nome da disciplina", "Nota 1","Nota 2","Nota 3","Nota 4", "Nota 5","Nota 6", "Nota 7", "Media", "Resultado"};
		Aluno aluno = new Aluno();
		Turma turma = new Turma();
		Disciplinas disciplina = new Disciplinas();
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		/*para completar as linhas da tabela com um laco, eu sei, eu sei, poderia ter feito melhor, mas nao consegui*/
		while (rs.next()){
			turma.setNomeTurma(rs.getString("turma.nomeTurma"));
			disciplina.setNomeDisciplina(rs.getString("disciplinas.nome"));
			notas.add(0, rs.getFloat("disciplinasDoAluno.nota_1"));
			notas.add(1, rs.getFloat("disciplinasDoAluno.nota_2"));
			notas.add(2, rs.getFloat("disciplinasDoAluno.nota_3"));
			notas.add(3, rs.getFloat("disciplinasDoAluno.nota_4"));
			notas.add(4, rs.getFloat("disciplinasDoAluno.nota_5"));
			notas.add(5, rs.getFloat("disciplinasDoAluno.nota_6"));
			notas.add(6, rs.getFloat("disciplinasDoAluno.nota_7"));
			aluno.setMedia(rs.getFloat("disciplinasDoAluno.media"));
			aluno.setResultado(rs.getString("disciplinasDoAluno.resultado"));
				/*adcionando na arrayList de objeto os resultSet's*/
		dados.add(new Object[] { 
					turma.getNomeTurma(),
					disciplina.getNomeDisciplina(),
					notas.get(0),
					notas.get(1),
					notas.get(2),
					notas.get(3),
					notas.get(4),
					notas.get(5),
					notas.get(6),
					aluno.getMedia(),
					aluno.getResultado()
				 });
		}
		ModeloTabela modelo = new ModeloTabela(dados, colunas);
		tbDisciplinas.setModel(modelo);
		tbDisciplinas.getColumnModel().getColumn(0).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(0).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(1).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(1).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(2).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(2).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(3).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(3).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(4).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(4).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(5).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(5).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(6).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(6).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(7).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(7).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(8).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(8).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(9).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(9).setResizable(true);
		tbDisciplinas.getColumnModel().getColumn(10).setPreferredWidth(141);
		tbDisciplinas.getColumnModel().getColumn(10).setResizable(true);
		tbDisciplinas.setAutoResizeMode(tbDisciplinas.AUTO_RESIZE_OFF);
		tbDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		stmt.close();
		rs.close();

	}
}
