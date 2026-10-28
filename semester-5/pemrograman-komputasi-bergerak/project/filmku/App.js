import React from 'react';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, ScrollView } from 'react-native';
import InfoFilm from './info-film';

export default function App() {
  const dataFilm = [
    {
      id: 0,
      judul: 'Project Hail Mary',
      gambar: require('./assets/projecthailmary.jpg'),
      genre1: 'Drama',
      genre2: 'Fantasy',
      rating: 4.5,
      detil: 'Science teacher Ryland Grace wakes up on a spaceship light years from home with no recollection of who he is or how he got there. As his memory returns, he begins to uncover his mission: solve the riddle of the mysterious substance causing the sun to die out. He must call on his scientific knowledge and unorthodox ideas to save everything on Earth from extinction… but an unexpected friendship means he may not have to do it alone.'
    },
    {
      id: 1,
      judul: 'Sore: A Wife from the Future',
      gambar: require('./assets/sore.jpg'),
      genre1: 'Drama',
      genre2: 'Romance',
      rating: 3.5,
      detil: 'A young man living alone in Croatia encounters a woman who claims to be his wife from the future, sent on a mission to help him correct his bad habits and improve his lifestyle.'
    },
    {
      id: 2,
      judul: 'The Hunger Games: Catching Fire',
      gambar: require('./assets/hunger.jpg'),
      genre1: 'Action',
      genre2: 'Drama',
      rating: 5,
      detil: 'After surviving the Hunger Games, Katniss and Peeta struggle with the consequences of their victory as unrest spreads across Panem. Forced back into the spotlight, they become symbols of hope and resistance while the Capitol prepares a new and deadly challenge that will change the future of the nation forever.'
    },
    {
      id: 3,
      judul: 'Knives Out',
      gambar: require('./assets/knivesout.jpg'),
      genre1: 'Mystery',
      genre2: 'Crime',
      rating: 4.5,
      detil: 'When young priest Jud Duplenticy is sent to assist charismatic firebrand Monsignor Jefferson Wicks, it’s clear that all is not well in the pews. After a sudden and seemingly impossible murder rocks the town, the lack of an obvious suspect prompts local police chief Geraldine Scott to join forces with renowned detective Benoit Blanc to unravel a mystery that defies all logic.'
    },
    {
      id: 4,
      judul: 'Perfect Days',
      gambar: require('./assets/perfectdays.jpg'),
      genre1: 'Drama',
      genre2: 'Slice of Life',
      rating: 5,
      detil: 'Hirayama is content with his life as a toilet cleaner in Tokyo. Outside of his structured routine, he cherishes music on cassette tapes, books, and taking photos of trees. Through unexpected encounters, he reflects on finding beauty in the world.'
    }
  ];
  // All film data credit belong to Letterboxd :)

  const daftarFilm = dataFilm.map((film) => (
    <InfoFilm key={film.id} data={film} />
  ));

  return (
    <ScrollView contentContainerStyle={styles.container}>
      {daftarFilm}
      <StatusBar style="auto" />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    flexDirection: 'column',
    justifyContent: 'flex-start'
  }
});
