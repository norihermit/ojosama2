export type DramaResponse = {
    "dramaName" : string;
    "dramaCountry" : string;
    "dramaIntro" : string;
    "dramaYear" : number,
    "dramaEpisode" :number
    "_links" : {
    "self" : {
        "href" : string;
    },
    "drama" : {
        "href" : string;
    }
    }
}
export type Drama = {
    "dramaName" : string;
    "dramaCountry" : string;
    "dramaIntro" : string;
    "dramaYear" : number;
    "dramaEpisode" :number;
}

export type DramaEntry = {
    drama: Drama;
    url: string;
}

