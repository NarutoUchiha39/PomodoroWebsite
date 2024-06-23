import React from 'react';
import { Button, Checkbox, Form, Input,Flex,Upload } from 'antd';
import {UploadOutlined } from '@ant-design/icons'
const onFinish = (values) => {

    console.log(values.file.originFileObj);
  
    const formData = new FormData()
    formData.append('title',values.title)
    formData.append('artist',values.artist)
    formData.append('credits',values.credits)
    formData.append('file',values.file.file.originFileObj)

    async function postSongs(){ 
            
        await fetch("http://localhost:8081/api/uploadSongs",{
            method:"POST",
            body: formData
        })
    }
    postSongs()
    
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};



const UploadSongs = () => (
    
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
                required: true,
                message: 'Please input title!',
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
                required: true,
                message: 'Please input artist name!',
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
                required: true,
                message: 'Please input credits!',
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
                required: true,
                message: 'Please input file!',
                },
            ]}
            >
            <Upload>
                <Button icon={<UploadOutlined />}>Click to Upload</Button>
            </Upload>
            </Form.Item>

            
            <Form.Item
            styles={{dsplay:'flex',width:'100%',display:'flex', justifyContent:'center',alignItems:'center'}}
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
);
export default UploadSongs;