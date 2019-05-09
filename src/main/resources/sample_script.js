// db.user.insertOne({ name: "test_u1", usertype: "admin", password: "test" });
// db.user.insertOne({ name: "test_u2", usertype: "admin", password: "test" });

db.notebook.insertOne({ userid: "5cd20beb34e63d3b072dfa3f", title: "test_nb1" });

db.note.insertOne({
    notebookid: "5cd20c6934e63d3b072dfa40",
    userid: "5cd20beb34e63d3b072dfa3f",
    content: {
        "entityMap": {},
        "blocks": [
            {
                "key": "fr3rp",
                "text": "",
                "type": "unstyled",
                "depth": 0,
                "inlineStyleRanges": [],
                "entityRanges": [],
                "data": {}
            }
        ]
    }
});

db.share.insertOne({
    noteid: "5cd20d6e34e63d3b072dfa41",
    fromid: "5cd20beb34e63d3b072dfa3f",
    toid: "5cd20e5534e63d3b072dfa42",
    accessLevel: "default"
});