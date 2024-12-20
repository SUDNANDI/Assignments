package com.demo.jdbc1;

import java.sql.*;
import java.util.*;
import java.util.Scanner;

class Player {
	private int id;
	private String name;
	private String skill;
	private int exp;
	private String country;
	private double overall_score;

	public Player(int id, String name, String skill, int exp, String country, double overall_score) {
		this.id = id;
		this.name = name;
		this.skill = skill;
		this.exp = exp;
		this.country = country;
		this.overall_score = overall_score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getOverallScore() {
		return overall_score;
	}

	public void setOverallScore(double overall_score) {
		this.overall_score = overall_score;
	}

	@Override
	public String toString() {
		return "Player{" + "id=" + id + ", name='" + name + '\'' + ", skill='" + skill + '\'' + ", exp=" + exp
				+ ", country='" + country + '\'' + ", overall_score=" + overall_score + '}';
	}
}

interface PlayerOperations {
	void addPlayer(Player player);

	Player getPlayerById(int id);

	List<Player> getAllPlayers();

	void updatePlayer(Player player);

	void deletePlayer(int id);

	List<Player> sortBySkill();

	List<Player> listByCountry(String country);

	List<Player> listByExperience();
}

abstract class AbstractPlayer implements PlayerOperations {
}


class PlayerDAOImpl extends AbstractPlayer {
	private Connection connection;

	public PlayerDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void addPlayer(Player player) {
		String query = "INSERT INTO players (name, skill, exp, country, overall_score) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getSkill());
			stmt.setInt(3, player.getExp());
			stmt.setString(4, player.getCountry());
			stmt.setDouble(5, player.getOverallScore());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Player getPlayerById(int id) {
		String query = "SELECT * FROM players WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"), rs.getInt("exp"),
						rs.getString("country"), rs.getDouble("overall_score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Player> getAllPlayers() {
		List<Player> players = new ArrayList<>();
		String query = "SELECT * FROM players";
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Player player = new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"),
						rs.getInt("exp"), rs.getString("country"), rs.getDouble("overall_score"));
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	@Override
	public void updatePlayer(Player player) {
		String query = "UPDATE players SET name = ?, skill = ?, exp = ?, country = ?, overall_score = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getSkill());
			stmt.setInt(3, player.getExp());
			stmt.setString(4, player.getCountry());
			stmt.setDouble(5, player.getOverallScore());
			stmt.setInt(6, player.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePlayer(int id) {
		String query = "DELETE FROM players WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Player> sortBySkill() {
		List<Player> players = getAllPlayers();
		players.sort(Comparator.comparing(Player::getSkill));
		return players;
	}

	@Override
	public List<Player> listByCountry(String country) {
		List<Player> players = new ArrayList<>();
		String query = "SELECT * FROM players WHERE country = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, country);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getString("skill"), rs.getInt("exp"),
						rs.getString("country"), rs.getDouble("overall_score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	@Override
	public List<Player> listByExperience() {
		List<Player> players = getAllPlayers();
		players.sort((p1, p2) -> Integer.compare(p2.getExp(), p1.getExp())); 
		return players;
	}
}

public class main {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/player_db";
		String username = "root";
		String password = "Capsql@2024"; 

		try (Connection connection = DriverManager.getConnection(url, username, password);
				Scanner scanner = new Scanner(System.in)) {

			PlayerDAOImpl playerDAO = new PlayerDAOImpl(connection);

			while (true) {
				System.out.println("Menu:");
				System.out.println("1. Add Player");
				System.out.println("2. View Players");
				System.out.println("3. Update Player");
				System.out.println("4. Delete Player");
				System.out.println("5. Sort Players by Skill");
				System.out.println("6. List Players by Country");
				System.out.println("7. List Players by Experience");
				System.out.println("8. Exit");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					System.out.print("Enter player name: ");
					String name = scanner.nextLine();
					System.out.print("Enter player skill (Bowler/Batsman): ");
					String skill = scanner.nextLine();
					System.out.print("Enter player experience (in years): ");
					int exp = scanner.nextInt();
					scanner.nextLine(); 
					System.out.print("Enter player country: ");
					String country = scanner.nextLine();
					System.out.print("Enter player overall score: ");
					double score = scanner.nextDouble();
					playerDAO.addPlayer(new Player(0, name, skill, exp, country, score));
					break;

				case 2:
					List<Player> players = playerDAO.getAllPlayers();
					for (Player player : players) {
						System.out.println(player);
					}
					break;

				case 3:
					System.out.print("Enter player ID to update: ");
					int updateId = scanner.nextInt();
					scanner.nextLine(); 
					Player playerToUpdate = playerDAO.getPlayerById(updateId);
					if (playerToUpdate != null) {
						System.out.print("Enter new player name: ");
						playerToUpdate.setName(scanner.nextLine());
						System.out.print("Enter new skill: ");
						playerToUpdate.setSkill(scanner.nextLine());
						System.out.print("Enter new experience: ");
						playerToUpdate.setExp(scanner.nextInt());
						scanner.nextLine(); 
						System.out.print("Enter new country: ");
						playerToUpdate.setCountry(scanner.nextLine());
						System.out.print("Enter new score: ");
						playerToUpdate.setOverallScore(scanner.nextDouble());
						playerDAO.updatePlayer(playerToUpdate);
					} else {
						System.out.println("Player not found!");
					}
					break;

				case 4:
					System.out.print("Enter player ID to delete: ");
					int deleteId = scanner.nextInt();
					playerDAO.deletePlayer(deleteId);
					break;

				case 5:
					List<Player> sortedPlayers = playerDAO.sortBySkill();
					sortedPlayers.forEach(System.out::println);
					break;

				case 6:
					System.out.print("Enter country to list players: ");
					String searchCountry = scanner.nextLine();
					List<Player> playersByCountry = playerDAO.listByCountry(searchCountry);
					playersByCountry.forEach(System.out::println);
					break;

				case 7:
					List<Player> playersByExp = playerDAO.listByExperience();
					playersByExp.forEach(System.out::println);
					break;

				case 8:
					System.out.println("Exiting...");
					return;

				default:
					System.out.println("Invalid choice. Try again.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
