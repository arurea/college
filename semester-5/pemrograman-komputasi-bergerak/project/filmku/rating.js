import React from 'react';
import { StyleSheet, View } from 'react-native';
import FontAwesome from '@expo/vector-icons/FontAwesome';

export default function Rating({ nilai }) {
    const jumlahStar = [1, 2, 3, 4, 5];

    const iconRating = jumlahStar.map((star) => {
        if (star <= Math.floor(nilai)) {
            return (
                <FontAwesome key={star} name="star" color="white"/>
            );
        } else if (star - nilai === 0.5) {
            return (
                <FontAwesome key={star} name="star-half" color="white"/>
            );
        } else {
            return (
                <FontAwesome key={star} name="star-o" color="white"/>
            );
        }
});

return (
    <View style={styles.rating}>
        {iconRating}
    </View>
    );
}

const styles = StyleSheet.create({
    rating: {
        flexDirection: 'row',
        justifyContent: 'space-evenly',
        width: 120,
        alignSelf: 'flex-end',
        marginRight: 30,
        marginTop: 8
    }
});
