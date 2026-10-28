import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

export default function Header({ teks }) {
    return (
        <View style={styles.header}>
            <Text style={styles.headerTeks}>{teks}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    header: {
        flexDirection: 'row',
        justifyContent: 'center',
        padding: 15
    },

    headerTeks: {
        color: 'white',
        fontSize: 18,
        fontWeight: 'bold',
        letterSpacing: 5
    }
});
