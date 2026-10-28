import React from 'react';
import {StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import Rating from './rating';

export default function ItemFilm({ data, detil, onPress }) {
    return (
        <View style={styles.info}>
        <Text style={styles.filmTeks}>{data.judul}</Text>

        <Rating nilai={data.rating}/>

        <View style={styles.separator}/>

        <Text style={styles.keterangan}>{data.genre1}</Text>
        <Text style={styles.keterangan}>{data.genre2}</Text>

        {detil ? (
            <Text style={styles.detil}>{data.detil}</Text>
        ) : null}

        <TouchableOpacity style={styles.tombol} onPress={onPress}>
            <Text style={styles.tombolTeks}>
                {detil ? 'See less...' : 'See more...'}
            </Text>
        </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    info: {
        justifyContent: 'flex-end'
    },

    filmTeks: {
        color: 'white',
        fontSize: 24,
        fontWeight: 'bold',
        paddingLeft: 30,
        paddingRight: 30
    },

    separator: {
        borderBottomColor: 'white',
        borderBottomWidth: 2,
        width: '85%',
        marginTop: 8,
        marginLeft: 30,
        marginBottom: 8
    },

    keterangan: {
        paddingLeft: 30,
        color: 'white',
        marginTop: 4
    },

    detil: {
        color: 'white',
        paddingLeft: 30,
        paddingRight: 30,
        marginTop: 8
    },

    tombol: {
        marginTop: 10,
        marginBottom: 10,
        marginRight: 30,
        paddingTop: 8,
        paddingBottom: 8,
        paddingLeft: 14,
        paddingRight: 14,
        backgroundColor: 'rgba(255, 255, 255, 0.25)',
        alignSelf: 'flex-end',
        borderRadius: 100
    },

    tombolTeks: {
        color: 'white'
    }
});
