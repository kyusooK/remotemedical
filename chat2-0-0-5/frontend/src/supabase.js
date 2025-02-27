// src/supabase.js
import { createClient } from '@supabase/supabase-js'

const supabaseUrl = 'https://54321-kyusook-remotemedical-1a83f4ezwga.ws-us118.gitpod.io/'
const supabaseKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZS1kZW1vIiwicm9sZSI6ImFub24iLCJleHAiOjE5ODM4MTI5OTZ9.CRXP1A7WOeoJeXxjNni43kdQwgnWNReilDMblYTn_I0'
export const supabase = createClient(supabaseUrl, supabaseKey)