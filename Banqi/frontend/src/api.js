import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `http://localhost:8080/api`,
  timeout: 100000,
  headers: {
    'Content-Type': 'application/json'
  }
});

export default {

  // User Endpoints
  registerUser(userData) {
    return AXIOS.post('/user/add', userData);
  },

  getAllUsers() {
    return AXIOS.get('/user/list');
  },

  getUser(id) {
    return AXIOS.get(`/user/${id}`);
  },

  getUserByUsername(username) {
    return AXIOS.get(`/user/byUsername/${username}`);
  },

  // Game Endpoints
  hello() {
    return AXIOS.get(`/game/hello`);
  },

  getExistingGame(gameId) {
    return AXIOS.get(`/game/${gameId}`);
  },

  getNewGame() {
    return AXIOS.get('/game/create');
  },

  createNewGame(id1, id2) {
    return AXIOS.get(`/game/create/${id1}/${id2}`);
  },

  updateUser(userInfo) {
    return AXIOS.post(`/user/edit`, userInfo);
  },

  getAllGames() {
    return AXIOS.get('game/admin/game/list/all');
  },

  deleteGame(id) {
    return AXIOS.get(`/game/${id}/delete`);
  },

  deleteUser(id) {
    return AXIOS.get(`/user/${id}/delete`);
  },

  getUsersGames(id) {
    return AXIOS.get(`/game/list/${id}`);
  },

  getValidMoves(coordinates, id) {
    return AXIOS.post(`/game/${id}/validMoves`, coordinates);
  },

  executeMove(coordinates, id, userId) {
    return AXIOS.post(`/game/${id}/executeMove/${userId}`, coordinates);
  },

  getMoveHistory(id) {
    return AXIOS.get(`/game/${id}/moveHistory`);
  },

  forfeitGameOver(gameId, userId) {
    return AXIOS.get(`/game/${gameId}/${userId}/forfeit`);
  }
}
