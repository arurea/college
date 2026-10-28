import React, { useState } from 'react';
import {StyleSheet,View,ImageBackground} from 'react-native';
import Header from './header';
import ItemFilm from './item-film';

export default function InfoFilm({ data }) {
    const [detil, setDetil] = useState(false);

    const handlePress = () => {
        setDetil(!detil);
    };

    return (
        <ImageBackground style={styles.gambarBG} source={data.gambar}imageStyle={styles.gambarSudut}>
        <Header teks="F I L M K U" />

        <View style={styles.bagianInfo}> <ItemFilm data={data} detil={detil}onPress={handlePress}/>
        </View>
        </ImageBackground>
    );
}

const styles = StyleSheet.create({
    gambarBG: {
        width: '100%',
        height: 450,
        justifyContent: 'space-between',
        paddingBottom: 15
    },

    gambarSudut: {
        resizeMode: 'cover'
    },

    bagianInfo: {
        justifyContent: 'flex-end'
    }
});
