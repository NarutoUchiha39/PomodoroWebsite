import React from 'react';
import { Button, Checkbox, Form, Input,Flex,Upload } from 'antd';
import {UploadOutlined } from '@ant-design/icons'
import { useEffect } from 'react';
import {useParams,useNavigate} from 'react-router-dom'



function EditSongs(props){

    let {id} = useParams()
    let navigate = useNavigate();

    let file = false

    const onFinish = (values) => {

        let length = values
        const formData = new FormData()
        
        Object.keys(values).forEach(key => {
            if(key!='file')
            {
                const value = values[key];
                formData.append(`${key}`,value)
            }else
            {
                if(values[key]!=undefined){
                    formData.append('file',values.file.file.originFileObj)
                }
            }
    });

       


        console.log(formData)

        async function postSongs(){ 

            try
            {
                
                await fetch(`http://localhost:8081/api/editSongs/${id}`,{
                    method:"POST",
                    body: formData
                })

            }catch{
                    alert("Song updates successfully !!");
                    return navigate("/")

            
            }
        }
        postSongs()

    
    };
    const onFinishFailed = (errorInfo) => {
      alert("Error in editing song :(")
    };
    


    useEffect(()=>{

        props.setHeader("Edit Songs")

    },[])

    return(
    
        <Flex justify='center' align='center' style={{boxShadow: 'rgba(0, 0, 0, 0.35) 0px 5px 15px',overflow:'hidden',background:'#608089ee', width:'fit-content', padding:'2rem', borderRadius: '5px', margin:'auto', marginTop:'20vh', marginLeft: '33rem'}}>
        <Form
            name="basic"
            layout='vertical'
            initialValues={{
            remember: true,
            }} 
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
        >
            <Form.Item
            label="Enter title"
            name="title"
            rules={[
                {
                required: false,
                },
            ]}
            >
            <Input />
            </Form.Item>


            <div style = {{display:'flex', gap:'5px'}}>
            <Form.Item
            label="Enter artist"
            name="artist"
            rules={[
                {
                required: false,
                },
            ]}
            >
            <Input/>
            </Form.Item>

            <Form.Item
            label="Enter Credits"
            name="credits"
            rules={[
                {
                required: false,
                
                },
            ]}
            >
            <Input/>
            </Form.Item>
            </div>
            <Form.Item
            label="Upload file"
            name="file"
            rules={[
                {
                required: false,
                },
            ]}
            >
            <Upload styles={{width:'100%'}}>
                <Button icon={<UploadOutlined />}>Click to Upload</Button>
            </Upload>
            </Form.Item>

            
            <Form.Item
            wrapperCol={{
                offset: 8,
                span: 16,
            }}
            >
            <Button type="primary" htmlType="submit">
                Submit
            </Button>
            </Form.Item>
        </Form>
    </Flex>
)};
export default EditSongs;