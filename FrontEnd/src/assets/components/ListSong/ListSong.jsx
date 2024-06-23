import { EditOutlined, DeleteOutlined } from '@ant-design/icons';
import { useState, useEffect } from 'react';
import { Avatar, Card, Flex, Button } from 'antd';
import './ListSong.css';
import { Link } from 'react-router-dom';
const { Meta } = Card;

const ListSong = (props) => {
  const [songs, setSongs] = useState([]);
  const [loading, setLoading] = useState(true);

  const onChange = (checked) => {
    setLoading(!checked);
  };

  async function deleteKaro(id){ 

    try{
        await fetch(`http://localhost:8081/api/deleteSongs/${id}`,{
            method:"GET"
        })
      }
      catch{
        
      }

}

  useEffect(() => {
    async function getSongsList() {
      let result = await fetch("http://localhost:8080/api/getSongsList", {
        method: "GET",
        headers: { "Authorization": `Bearer ${props.user.token}` }
      }).then(async (res) => { return await res.json() });
      setSongs(result);
      onChange(loading);
    }

    getSongsList();

  }, []);

  return (
    <>
      <Flex wrap='wrap' gap={40} style={{ position: 'relative', left: "20%", width: "80%", marginTop: '15vh' }}>
        {
          songs.map((element, index) => (
            <Card
              style={{
                width: 300,
                marginTop: 19,
                maxHeight: 100,
                border: "1px solid black"
              }}
              bordered={true}
              loading={loading}
              actions={[
                <Link to={`/EditSong/${element.url}`}><EditOutlined key="edit" /></Link>,
                <Button type="text" onClick={()=>deleteKaro(element.url)}><DeleteOutlined key="delete" /></Button>
              ]}
              key={index}
              hoverable={true}
            >
              <Meta
                avatar={<Avatar src="https://api.dicebear.com/7.x/miniavs/svg?seed=2" />}
                title={`${element.title}`}
                description={`${element.artist}`}
              />
            </Card>
          ))
        }
      </Flex>
    </>
  )
}

export default ListSong;
